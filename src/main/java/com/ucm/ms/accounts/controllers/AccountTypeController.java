package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.dto.RequestAccountTypeDto;
import com.ucm.ms.accounts.entities.AccountType;
import com.ucm.ms.accounts.services.AccountSearch;
import com.ucm.ms.accounts.services.AccountTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountTypeController {
	
	@Autowired
    AccountSearch accountSearch;
	
	@Autowired
	AccountTypeService accountTypeService;

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
    
    /**
     * Used for creating new account types
     * @param RequestAccountTypeDto
     * 
     */
    @PostMapping
	public void createAccountType(@RequestBody RequestAccountTypeDto accountTypeInfo) {
		accountTypeService.createAccountType(accountTypeInfo);
	}

    /**
     * Used for deleting account types
     * @param Integer id of the account types
     * 
     */
    @DeleteMapping("/{id}")
	public void deleteAccountType(@PathVariable Integer id) {
		accountTypeService.deleteAccountType(id);
	}
}