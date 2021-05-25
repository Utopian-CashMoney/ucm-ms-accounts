package com.ucm.ms.accounts.services;

import com.ucm.lib.dao.UserDAO;
import com.ucm.lib.entities.User;
import com.ucm.lib.services.JwtUtil;
import com.ucm.ms.accounts.dao.AccountDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.dto.RegisterUserAccountDTO;
import com.ucm.ms.accounts.dto.RegisterUserAccountRespDTO;
import com.ucm.ms.accounts.entities.Account;
import com.ucm.ms.accounts.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Random;

/**
 * Service for registration of user accounts.
 * @author Joshua Podhola
 */
@Service
@Transactional
public class UserAccountRegistration {
    private final UserAccountDAO userAccountDAO;
    private final UserDAO userDAO;
    private final AccountDAO accountDAO;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserAccountRegistration(UserAccountDAO userAccountDAO, UserDAO userDAO, AccountDAO accountDAO, JwtUtil jwtUtil) {
        this.userAccountDAO = userAccountDAO;
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.jwtUtil = jwtUtil;
    }

    public RegisterUserAccountRespDTO register(RegisterUserAccountDTO registerUserAccountDTO, String token) {
        UserAccount userAccount = new UserAccount();
        User user = userDAO.findByUsername(jwtUtil.getUserNameFromJwtToken(token));
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authentication token failed to resolve to a valid user.");
        }

        Account account = accountDAO.findById(registerUserAccountDTO.getAccountID()).orElse(null);
        if(account == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No account with that ID.");
        }


        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setBalance(new BigDecimal(0));
        userAccount.setAccountNumber(randomAccountNumber());
        userAccountDAO.save(userAccount);
        RegisterUserAccountRespDTO registerUserAccountRespDTO = new RegisterUserAccountRespDTO();
        registerUserAccountRespDTO.setAccountNumber(userAccount.getAccountNumber());
        registerUserAccountRespDTO.setAccountType(userAccount.getAccount().getType());
        registerUserAccountRespDTO.setAccountName(userAccount.getAccount().getName());
        return registerUserAccountRespDTO;
    }

    protected String randomAccountNumber() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }
}
