package com.ucm.ms.accounts.services;

//import com.ucm.ms.accounts.dao.AccountDAO;
//import com.ucm.ms.accounts.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Services designed to fetch collections of Accounts
 */
@Service
@Transactional
public class AccountSearch {
//    private final AccountDAO accountDAO;
//
//    /**
//     * Uses constructor-based dependency injection.
//     * @param accountDAO The Account's DAO
//     */
//    @Autowired
//    public AccountSearch(AccountDAO accountDAO) {
//        this.accountDAO = accountDAO;
//    }
//
//    /**
//     * Get the first 50 results for all accounts.
//     * @return Up to 50 results in a collection. Returns null in event of an error.
//     */
//    public Collection<Account> getFirst50() {
//        return accountDAO.findTop50ByOrderById();
//    }
}
