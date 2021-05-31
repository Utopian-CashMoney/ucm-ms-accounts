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
import com.ucm.ms.accounts.entities.UserAccount;
import com.ucm.ms.accounts.entities.UserAccountConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

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
    private final UserAccountConfirmationDAO userAccountConfirmationDAO;

    @Autowired
    public UserAccountRegistration(UserAccountDAO userAccountDAO, UserDAO userDAO, AccountDAO accountDAO,
                                   JwtUtil jwtUtil, UserAccountConfirmationDAO userAccountConfirmationDAO) {
        this.userAccountDAO = userAccountDAO;
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.jwtUtil = jwtUtil;
        this.userAccountConfirmationDAO = userAccountConfirmationDAO;
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
        UserAccountConfirmation userAccountConfirmation = generateConfirmation(userAccount);
        userAccountConfirmationDAO.save(userAccountConfirmation);
        RegisterUserAccountRespDTO registerUserAccountRespDTO = new RegisterUserAccountRespDTO();
        registerUserAccountRespDTO.setAccountNumber(userAccount.getAccountNumber());
        registerUserAccountRespDTO.setAccountType(userAccount.getAccount().getType());
        registerUserAccountRespDTO.setAccountName(userAccount.getAccount().getName());
        return registerUserAccountRespDTO;
    }

    /**
     * Generates a UserAccountConfirmation to the user. Does not save.
     * @param userAccount The UserAccount to confirm.
     * @return A UserAccountConfirmation.
     */
    protected UserAccountConfirmation generateConfirmation(UserAccount userAccount) {
        UserAccountConfirmation userAccountConfirmation = new UserAccountConfirmation();
        userAccountConfirmation.setUserAccount(userAccount);
        userAccountConfirmation.setExpires(LocalDateTime.now().plusDays(1));
        String s;
        do {
            s = UUID.randomUUID().toString();
            userAccountConfirmation.setCode(s);
        } while(userAccountConfirmationDAO.findFirstByCode(s) != null);
        return userAccountConfirmation;
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
