//package com.ucm.ms.accounts.services;
//
//import com.ucm.lib.config.util.JwtUtil;
//import com.ucm.lib.dao.UserDAO;
//import com.ucm.lib.entities.User;
//import com.ucm.lib.services.EmailService;
//import com.ucm.lib.services.VerificationService;
////import com.ucm.ms.accounts.dao.AccountDAO;
//import com.ucm.ms.accounts.dao.UserAccountConfirmationDAO;
//import com.ucm.ms.accounts.dao.UserAccountDAO;
//import com.ucm.ms.accounts.dto.RegisterUserAccountDTO;
//import com.ucm.ms.accounts.dto.RegisterUserAccountRespDTO;
////import com.ucm.ms.accounts.entities.Account;
//import com.ucm.ms.accounts.entities.UserAccount;
//import com.ucm.ms.accounts.entities.UserAccountConfirmation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//import org.thymeleaf.context.Context;
//
//import javax.mail.MessagingException;
//import javax.transaction.Transactional;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Random;
//import java.util.UUID;
//
///**
// * Service for registration of user accounts.
// * @author Joshua Podhola
// */
//@Service
//@Transactional
//public class UserAccountRegistration {
//   // private final UserAccountDAO userAccountDAO;
//   // private final UserDAO userDAO;
////    private final AccountDAO accountDAO;
////    private final JwtUtil jwtUtil;
////    private final UserAccountConfirmationDAO userAccountConfirmationDAO;
////    private final EmailService emailService;
//  //  private final VerificationService<UserAccount, UserAccountConfirmation, UserAccountDAO, UserAccountConfirmationDAO> verificationService;
//
//    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
//
////    @Autowired
////    public UserAccountRegistration(UserAccountDAO userAccountDAO, UserDAO userDAO, AccountDAO accountDAO,
////                                   JwtUtil jwtUtil, UserAccountConfirmationDAO userAccountConfirmationDAO,
////                                   EmailService emailService, VerificationService<UserAccount, UserAccountConfirmation, UserAccountDAO, UserAccountConfirmationDAO> verificationService) {
////        this.userAccountDAO = userAccountDAO;
////        this.userDAO = userDAO;
////        this.accountDAO = accountDAO;
////        this.jwtUtil = jwtUtil;
////        this.userAccountConfirmationDAO = userAccountConfirmationDAO;
////        this.emailService = emailService;
////        this.verificationService = verificationService;
////    }
//
////    public RegisterUserAccountRespDTO register(RegisterUserAccountDTO registerUserAccountDTO, String token) {
////        UserAccount userAccount = new UserAccount();
////        User user = userDAO.findByUsername(jwtUtil.getUserNameFromJwtToken(token));
////        if(user == null) {
////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authentication token failed to resolve to a valid user.");
////        }
////
////        Account account = accountDAO.findById(registerUserAccountDTO.getAccountID()).orElse(null);
////        if(account == null){
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No account with that ID.");
////        }
////
////        userAccount.setAccount(account);
////        userAccount.setUser(user);
////        userAccount.setBalance(new BigDecimal(0));
////        userAccount.setAccountNumber(randomAccountNumber());
////        userAccountDAO.save(userAccount);
////
////        UserAccountConfirmation userAccountConfirmation = generateConfirmation(userAccount);
////
////        verificationService.sendVerificationEmail(userAccountConfirmation, userAccount.getUser().getEmail(), "html/confirm_account");
////
////        RegisterUserAccountRespDTO registerUserAccountRespDTO = new RegisterUserAccountRespDTO();
////        registerUserAccountRespDTO.setAccountNumber(userAccount.getAccountNumber());
////        registerUserAccountRespDTO.setAccountType(userAccount.getAccount().getType());
////        registerUserAccountRespDTO.setAccountName(userAccount.getAccount().getName());
////        return registerUserAccountRespDTO;
////    }
//
//    /**
//     * Activate an account.
//     * @param confirmationToken The token of the account to activate.
//     * @return True on success, false if expired.
//     */
////    public Boolean confirm(String confirmationToken) {
////        return verificationService.confirm(confirmationToken);
////    }
//
//    /**
//     * Generates a UserAccountConfirmation to the user, then saves it.
//     * @param userAccount The UserAccount to confirm.
//     * @return A UserAccountConfirmation.
//     */
////    protected UserAccountConfirmation generateConfirmation(UserAccount userAccount) {
////        UserAccountConfirmation userAccountConfirmation = new UserAccountConfirmation();
////        return verificationService.generateConfirmation(userAccount, userAccountConfirmation);
////    }
//
//    protected String randomAccountNumber() {
//        String SALTCHARS = "1234567890";
//        StringBuilder salt = new StringBuilder();
//        Random rnd = new Random();
//        while (salt.length() < 12) { // length of the random string.
//            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//            salt.append(SALTCHARS.charAt(index));
//        }
//        return salt.toString();
//
//    }
//}
