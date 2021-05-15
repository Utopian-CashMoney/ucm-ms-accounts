package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

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
        Account account = DAOTestUtil.account;
        account = accountDAO.save(account);
        Account is = accountDAO.getOne(account.getId());
        assertEquals(is, account);
    }
}