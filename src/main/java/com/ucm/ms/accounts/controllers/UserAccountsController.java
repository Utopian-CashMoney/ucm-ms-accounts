package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_account")
@CrossOrigin
public class UserAccountsController {
    /**
     * POST /api/user_account - Create a new user account.
     * @return UserAccount
     */
    @PostMapping()
    public ResponseEntity<UserAccount> post() {
        return null;
    }
}
