//package com.ucm.ms.accounts.services;
//
//import com.ucm.lib.config.util.JwtUtil;
//import com.ucm.lib.dao.UserDAO;
//import com.ucm.lib.entities.User;
//import com.ucm.ms.accounts.dao.AccountDAO;
//import com.ucm.ms.accounts.dao.UserAccountConfirmationDAO;
//import com.ucm.ms.accounts.dao.UserAccountDAO;
//import com.ucm.ms.accounts.dto.RegisterUserAccountDTO;
//import com.ucm.ms.accounts.dto.RegisterUserAccountRespDTO;
//import com.ucm.ms.accounts.entities.Account;
//import com.ucm.ms.accounts.entities.UserAccount;
//import com.ucm.ms.accounts.entities.UserAccountConfirmation;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.web.client.HttpStatusCodeException;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ActiveProfiles("test")
//@SpringBootTest
//@AutoConfigureTestDatabase
//class UserAccountRegistrationTest {
//    //Mocked
//    @MockBean
//    JwtUtil jwtUtil;
//    @MockBean
//    AccountDAO accountDAO;
//    @MockBean
//    UserDAO userDAO;
//    @MockBean
//    UserAccountConfirmationDAO userAccountConfirmationDAO;
//    @MockBean
//    UserAccountDAO userAccountDAO;
//    @Autowired
//    UserAccountRegistration userAccountRegistration;
//
//    /**
//     * @author Joshua Podhola
//     */
//    @Test
//    void registerTest() {
//        //STEP 1: Arrange
//        //Input from the controller
//        RegisterUserAccountDTO registerUserAccountDTO = new RegisterUserAccountDTO();
//        registerUserAccountDTO.setAccountID(1); //We want to register for account 1 (present in data.sql)
//        //Input from the database. Only populate what we care about.
//        Account account = new Account();
//        account.setId(1);
//        account.setName("NAME");
//        account.setType("DEBIT");
//        when(accountDAO.findById(1)).thenReturn(java.util.Optional.of(account));
//
//        String token = "token";
//        User user = new User();
//        user.setId(1);
//        user.setUsername("username");
//        user.setEmail("user@website.com"); //Needed because else an IllegalArgumentException will be thrown.
//        when(jwtUtil.getUserNameFromJwtToken(token)).thenReturn(user.getUsername());
//        when(userDAO.findByUsername(user.getUsername())).thenReturn(user);
//
//        when(userAccountConfirmationDAO.findFirstByCode(anyString())).thenReturn(null); //For making sure that any randomly generated confirmation code is fine.
//        when(userAccountConfirmationDAO.save(any())).thenAnswer(x -> x.getArguments()[0]);
//
//        //STEP 2: Act
//        RegisterUserAccountRespDTO response = userAccountRegistration.register(registerUserAccountDTO, token);
//        //STEP 3: Assert
//        assertEquals(account.getName(), response.getAccountName());
//        assertEquals(account.getType(), response.getAccountType());
//        assertNotNull(response.getAccountNumber()); //No way to know what it is, but whatever it is will likely be fine.
//
//        verify(userAccountConfirmationDAO, times(1)).findFirstByCode(anyString());
//        verify(userAccountConfirmationDAO, times(1)).save(any());
//        verify(userAccountDAO, times(1)).save(any());
//    }
//
//    /**
//     * @author Joshua Podhola
//     */
//    @Test
//    void confirmTest() {
//        //STEP 1: Arrange
//        //Just have the same UserAccount for all scenarios.
//        UserAccount userAccount = new UserAccount();
//        userAccount.setActive(false);
//        userAccount.setAccountNumber("AAAAAAAAAA"); //venting my internal screaming and crying through the code that i write
//        String token_success = "token_success";
//        UserAccountConfirmation success = new UserAccountConfirmation();
//        success.setUserAccount(userAccount);
//        success.setCode(token_success);
//        success.setExpires(LocalDateTime.now().plusDays(1));
//        String token_expired = "token_failure";
//        UserAccountConfirmation expired = new UserAccountConfirmation();
//        expired.setUserAccount(userAccount);
//        expired.setCode(token_expired);
//        expired.setExpires(LocalDateTime.now().minusDays(1));
//        String token_invalid = "token_invalid";
//        //All should call userAccountConfirmationDAO.findFirstByCode(...) once.
//        when(userAccountConfirmationDAO.findFirstByCode(token_success)).thenReturn(success);
//        when(userAccountConfirmationDAO.findFirstByCode(token_expired)).thenReturn(expired);
//        when(userAccountConfirmationDAO.findFirstByCode(token_invalid)).thenReturn(null);
//        //On success, should call save on userAccountDAO once. Not interested in the value; leave as do nothing.
//        //On expired, should call generateConfirmation(...) and return false.
//        when(userAccountDAO.getOne(userAccount.getAccountNumber())).thenReturn(userAccount);
//        //On invalid, should raise a 404 exception.
//        //STEP 2: Act
//        System.out.println(userAccountConfirmationDAO.findFirstByCode(token_success));
//        Boolean result_valid = userAccountRegistration.confirm(token_success);
//        Boolean result_expired = userAccountRegistration.confirm(token_expired);
//        //STEP 3: Assert
//        assertThrows(ResponseStatusException.class, () -> userAccountRegistration.confirm(token_invalid));
//        assertTrue(result_valid);
//        assertFalse(result_expired);
//    }
//
//    /**
//     * @author Joshua Podhola
//     */
//    @Test
//    void generateConfirmationTest() {
//        //STEP 1: Arrange
//        UserAccount userAccount = new UserAccount();
//        userAccount.setAccountNumber("Something");
//        when(userAccountConfirmationDAO.findFirstByCode(anyString())).thenReturn(null);
//        when(userAccountConfirmationDAO.save(any())).thenAnswer(x -> x.getArguments()[0]);
//        //STEP 2: Act
//        UserAccountConfirmation result = userAccountRegistration.generateConfirmation(userAccount);
//        //STEP 3: Assert
//        assertEquals(userAccount, result.getUserAccount());
//        assertNotNull(result.getCode());
//    }
//}