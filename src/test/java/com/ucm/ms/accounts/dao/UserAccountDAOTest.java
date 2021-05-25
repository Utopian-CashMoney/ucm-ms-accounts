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

    /**
     * Tests to make sure the DAO and underlying Entity are working as intended.
     */
    @Test
    void sanityTest() {
        UserAccount userAccount = userAccountDAO.getOne("CHKACCTNMBR");
        assertNotNull(userAccount);
    }
}