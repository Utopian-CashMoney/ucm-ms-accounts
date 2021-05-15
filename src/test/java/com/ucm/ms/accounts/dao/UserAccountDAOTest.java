package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.Account;
import com.ucm.ms.accounts.entities.Card;
import com.ucm.ms.accounts.entities.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserAccountDAOTest {
    @Autowired
    private UserAccountDAO userAccountDAO;
    @Autowired
    private AccountDAO accountDAO;

    /**
     * Tests to make sure the DAO and underlying Entity are working as intended.
     */
    @Test
    void sanityTest() {
        //Look, I don't understand why this is necessary for this DAO but not the others, but that's how it is.
        //I don't have time for this, I have other things to do. -JP
        Account account = accountDAO.save(DAOTestUtil.account);
        UserAccount userAccount = DAOTestUtil.userAccount;
        userAccount.setAccount(account);
        userAccount = userAccountDAO.save(userAccount);
        UserAccount get = userAccountDAO.getOne(userAccount.getAccountNumber());
        assertEquals(get, userAccount);
    }
}