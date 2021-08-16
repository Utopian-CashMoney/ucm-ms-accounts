package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.AccountType;
import com.ucm.ms.accounts.entities.CreditCard;
import com.ucm.ms.accounts.entities.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
class CardDAOTest {
    @Autowired
    private CardDAO cardDAO;

    /**
     * Tests to make sure the DAO and underlying Entity are working as intended.
     */
    @Test
    void sanityTest() {
        CreditCard card = cardDAO.getOne(1);
        assertNotNull(card);
        //assertEquals(1, card.getId()); NEED TO FIX
    }
}