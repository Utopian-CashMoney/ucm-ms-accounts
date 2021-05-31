package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.UserAccountConfirmation;
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
class UserAccountConfirmationDAOTest {
    @Autowired
    private UserAccountConfirmationDAO userAccountConfirmationDAO;

    @Autowired
    private UserAccountDAO userAccountDAO;

    @Test
    void findFirstByCodeTest() {
        //Present in data.sql
        UserAccountConfirmation userAccountConfirmation = userAccountConfirmationDAO.findFirstByCode("code");
        assertNotNull(userAccountConfirmation);
        assertEquals("code", userAccountConfirmation.getCode());
    }

    @Test
    void saveTest() {
        UserAccountConfirmation userAccountConfirmation = new UserAccountConfirmation();
        userAccountConfirmation.setUserAccount(userAccountDAO.getOne("CRDTACCTNMBR"));
        userAccountConfirmation.setCode("coooode");
        userAccountConfirmation.setExpires(LocalDateTime.now().plusDays(1));
        userAccountConfirmationDAO.save(userAccountConfirmation);
    }
}