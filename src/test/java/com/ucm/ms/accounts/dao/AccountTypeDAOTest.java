package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.AccountType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
class AccountTypeDAOTest {
    @Autowired
    private AccountTypeDAO accountDAO;

    /**
     * Tests to make sure the DAO and underlying Entity are working as intended.
     */
    @Test
    void sanityTest() {
        AccountType account = accountDAO.getOne(1);
        assertNotNull(account);
        assertEquals(1, account.getId());
    }

    /**
     * @author Joshua Podhola
     */
    @Test
    void findTop50Test() {
        //Add more than 50 tests. Probably not efficient, but that's really not what I'm concerned about.
        for(int i = 0; i != 60; i++) {
            AccountType account = new AccountType(String.format("TestAccount%d", i), "DEBIT", false, 0.0f, false, 0.0f, "text");
            accountDAO.save(account);
        }
        Collection<AccountType> accounts = accountDAO.findTop50ByOrderById();
        assertEquals(50, accounts.size());
    }
}