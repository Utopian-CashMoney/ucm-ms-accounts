package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountDAOTest {
    @Autowired
    private AccountDAO accountDAO;

    /**
     * Tests to make sure the DAO and underlying Entity are working as intended.
     */
    @Test
    void sanityTest() {
        Account account = accountDAO.getOne(1);
        assertNotNull(account);
    }

    /**
     * @author Joshua Podhola
     */
    @Test
    void findTop50Test() {
        //Add more than 50 tests. Probably not efficient, but that's really not what I'm concerned about.
        for(int i = 0; i != 60; i++) {
            Account account = new Account(String.format("TestAccount%d", i), "DEBIT", false, 0.0f, false);
            accountDAO.save(account);
        }
        Collection<Account> accounts = accountDAO.findTop50ByOrderById();
        assertEquals(50, accounts.size());
    }
}