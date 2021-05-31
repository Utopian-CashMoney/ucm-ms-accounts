package com.ucm.ms.accounts.services;

import com.ucm.lib.dao.UserDAO;
import com.ucm.lib.entities.User;
import com.ucm.lib.services.JwtUtil;
import com.ucm.ms.accounts.dao.AccountDAO;
import com.ucm.ms.accounts.dao.UserAccountConfirmationDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.dto.RegisterUserAccountDTO;
import com.ucm.ms.accounts.dto.RegisterUserAccountRespDTO;
import com.ucm.ms.accounts.entities.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
class UserAccountRegistrationTest {
    //Real
    @Autowired
    UserAccountRegistration userAccountRegistration;
    //Mocked
    @MockBean
    JwtUtil jwtUtil;
    @MockBean
    AccountDAO accountDAO;
    @MockBean
    UserDAO userDAO;
    @MockBean
    UserAccountConfirmationDAO userAccountConfirmationDAO;
    @MockBean
    UserAccountDAO userAccountDAO;

    /**
     * @author Joshua Podhola
     */
    @Test
    void registerTest() {
        //STEP 1: Arrange
        //Input from the controller
        RegisterUserAccountDTO registerUserAccountDTO = new RegisterUserAccountDTO();
        registerUserAccountDTO.setAccountID(1); //We want to register for account 1 (present in data.sql)
        //Input from the database. Only populate what we care about.
        Account account = new Account();
        account.setId(1);
        account.setName("NAME");
        account.setType("DEBIT");
        when(accountDAO.findById(1)).thenReturn(java.util.Optional.of(account));

        String token = "token";
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        when(jwtUtil.getUserNameFromJwtToken(token)).thenReturn(user.getUsername());
        when(userDAO.findByUsername(user.getUsername())).thenReturn(user);

        when(userAccountConfirmationDAO.findFirstByCode(anyString())).thenReturn(null); //For making sure that any randomly generated confirmation code is fine.

        //STEP 2: Act
        RegisterUserAccountRespDTO response = userAccountRegistration.register(registerUserAccountDTO, token);
        //STEP 3: Assert
        assertEquals(account.getName(), response.getAccountName());
        assertEquals(account.getType(), response.getAccountType());
        assertNotNull(response.getAccountNumber()); //No way to know what it is, but whatever it is will likely be fine.

        verify(userAccountConfirmationDAO, times(1)).findFirstByCode(anyString());
        verify(userAccountConfirmationDAO, times(1)).save(any());
        verify(userAccountDAO, times(1)).save(any());
    }
}