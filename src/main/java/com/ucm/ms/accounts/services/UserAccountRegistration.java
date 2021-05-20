package com.ucm.ms.accounts.services;

import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service for registration of user accounts.
 * @author Joshua Podhola
 */
@Service
@Transactional
public class UserAccountRegistration {
    private final UserAccountDAO userAccountDAO;

    @Autowired
    public UserAccountRegistration(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    public UserAccount register() {
        return null;
    }
}
