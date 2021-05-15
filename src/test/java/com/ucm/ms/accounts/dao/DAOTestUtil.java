package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.Account;
import com.ucm.ms.accounts.entities.Card;
import com.ucm.ms.accounts.entities.Transaction;
import com.ucm.ms.accounts.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Utility class to make unit testing less annoying.
 */
public class DAOTestUtil {
    public static Account account = new Account("TestAccount", "DEBIT", false, 0.0f, false);
    public static UserAccount userAccount = new UserAccount("ACCOUNTNUMBER", 0, account);
    public static Transaction transaction = new Transaction(new BigDecimal("0.0"), LocalDateTime.now(), "SomeTransaction", false, false, userAccount);
    public static Card card = new Card("Cardnumber", LocalDate.now(), "666", userAccount);
}
