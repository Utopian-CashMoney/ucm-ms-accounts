package com.ucm.ms.accounts.services;

import com.ucm.lib.config.util.JwtUtil;
import com.ucm.lib.dao.UserDAO;
import com.ucm.lib.entities.User;
import com.ucm.lib.services.EmailService;
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
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final EmailService emailService;

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

    @Autowired
    public UserAccountRegistration(UserAccountDAO userAccountDAO, UserDAO userDAO, AccountDAO accountDAO,
                                   JwtUtil jwtUtil, UserAccountConfirmationDAO userAccountConfirmationDAO,
                                   EmailService emailService) {
        this.userAccountDAO = userAccountDAO;
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.jwtUtil = jwtUtil;
        this.userAccountConfirmationDAO = userAccountConfirmationDAO;
        this.emailService = emailService;
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

        try {
            Context context = new Context();
            context.setVariable("expiration", userAccountConfirmation.getExpires().format(DATETIME_FORMATTER));
            context.setVariable("confirmation_code", userAccountConfirmation.getCode());
            emailService.sendEmail(user.getEmail(), context,"html/confirm_account","Account Registration Verification Required");
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Failed to send confirmation email.", e);
        }

        RegisterUserAccountRespDTO registerUserAccountRespDTO = new RegisterUserAccountRespDTO();
        registerUserAccountRespDTO.setAccountNumber(userAccount.getAccountNumber());
        registerUserAccountRespDTO.setAccountType(userAccount.getAccount().getType());
        registerUserAccountRespDTO.setAccountName(userAccount.getAccount().getName());
        return registerUserAccountRespDTO;
    }

    /**
     * Activate an account.
     * @param confirmationToken The token of the account to activate.
     * @return True on success, false if expired.
     */
    public Boolean confirm(String confirmationToken) {
        UserAccountConfirmation userAccountConfirmation = userAccountConfirmationDAO.findFirstByCode(confirmationToken);
        if(userAccountConfirmation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No confirmation found with that token.");
        }
        if (userAccountConfirmation.getExpires().isBefore(LocalDateTime.now())) {
            userAccountConfirmationDAO.delete(userAccountConfirmation);
            generateConfirmation(userAccountDAO.getOne(userAccountConfirmation.getUserAccountId()));
            return false;
        }
        else {
            UserAccount userAccount = userAccountConfirmation.getUserAccount();
            userAccount.setActive(true);
            userAccountDAO.save(userAccount);
            userAccountConfirmationDAO.delete(userAccountConfirmation);
            return true;
        }

    }

    /**
     * Generates a UserAccountConfirmation to the user, then saves it.
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
        return userAccountConfirmationDAO.save(userAccountConfirmation);
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
