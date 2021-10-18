//package com.ucm.ms.accounts.config;
//
//import com.ucm.lib.services.EmailService;
//import com.ucm.lib.services.VerificationService;
//import com.ucm.ms.accounts.dao.UserAccountConfirmationDAO;
//import com.ucm.ms.accounts.dao.UserAccountDAO;
//import com.ucm.ms.accounts.entities.UserAccount;
//import com.ucm.ms.accounts.entities.UserAccountConfirmation;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//    final UserAccountDAO userAccountDAO;
//    final UserAccountConfirmationDAO userAccountConfirmationDAO;
//    final EmailService emailService;
//
//    @Autowired
//    public AppConfig(UserAccountDAO userAccountDAO, UserAccountConfirmationDAO userAccountConfirmationDAO, EmailService emailService) {
//        this.userAccountDAO = userAccountDAO;
//        this.userAccountConfirmationDAO = userAccountConfirmationDAO;
//        this.emailService = emailService;
//    }
//
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//
//    /**
//     * Bean for UserAccount confirmation
//     * @return UserAccountConfirmationService
//     */
//    @Bean
//    public VerificationService<UserAccount, UserAccountConfirmation, UserAccountDAO, UserAccountConfirmationDAO> verificationService() {
//        return new VerificationService<>(userAccountConfirmationDAO, userAccountDAO, emailService);
//    }
//}
