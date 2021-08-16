package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.CreditCard;
import com.ucm.ms.accounts.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
class TransactionDAOTest {
    @Autowired
    private TransactionDAO transactionDAO;

    /**
     * Tests to make sure the DAO and underlying Entity are working as intended.
     */
    @Test
    void sanityTest() {
        Transaction transaction = transactionDAO.getOne(1);
        assertNotNull(transaction);
        assertEquals(1, transaction.getId());
    }
}