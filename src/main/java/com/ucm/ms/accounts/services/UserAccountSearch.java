package com.ucm.ms.accounts.services;

import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

import java.util.Collection;

/**
 * Services designed to fetch collections of UserAccounts
 */
@Service
@Transactional
public class UserAccountSearch {
    private final UserAccountDAO userAccountDAO;

    /**
     * Uses constructor-based dependency injection.
     * @param userAccountDAO The UserAccount's DAO
     */
    @Autowired
    public UserAccountSearch(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }
    
    /**
	 * Get all UserAccounts in DB
	 * 
	 * @return Collection of all userAccounts
	 */
	public Collection<UserAccount> getAll() {
		return userAccountDAO.findAll();
	}
}
