package com.ucm.ms.accounts.config.profiles;

import com.ucm.lib.dao.UserDAO;
import com.ucm.lib.entities.User;
import com.ucm.ms.accounts.dao.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
public class LocalProfile {
    /**
     * Initialize values into the database.
     * @param accountDAO DI AccountDAO
     * @param cardDAO DI CardDAO
     * @param transactionDAO DI TransactionDAO
     * @param userAccountConfirmationDAO DI UserAccountConfirmationDAO
     * @param userAccountDAO DI UserAccountDAO
     * @param userDAO DI UserDAO
     * @return An automatically ran lambda function that populates the database.
     */
    @Bean
    public CommandLineRunner initData(
            final AccountDAO accountDAO,
            final CardDAO cardDAO,
            final TransactionDAO transactionDAO,
            final UserAccountConfirmationDAO userAccountConfirmationDAO,
            final UserAccountDAO userAccountDAO,
            final UserDAO userDAO) {
        return (args) -> {
            //TODO: User constructor needs to have a roles parameter.
            userDAO.save(new User("username1", "email@email.com", "password", "", "John", "Wick"));

            //TODO: Populate with data from other classes.
        };
    }
}
