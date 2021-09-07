package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.entities.AccountType;
import com.ucm.ms.accounts.services.AccountSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountTypeController {
    private final AccountSearch accountSearch;

    @Autowired
    public AccountTypeController(AccountSearch accountSearch) {
        this.accountSearch = accountSearch;
    }

    /**
     * GET /api/accounts - Return all accounts
     * @return First 50 accounts
     */
    @GetMapping
    public ResponseEntity<Collection<AccountType>> get() {
        try {
            Collection<AccountType> accounts = accountSearch.getFirst50();
            return new ResponseEntity<>(accounts, HttpStatus.valueOf(200));
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
