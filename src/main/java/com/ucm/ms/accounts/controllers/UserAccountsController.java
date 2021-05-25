package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.dto.UserAccountDTO;
import com.ucm.ms.accounts.entities.UserAccount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_account")
@CrossOrigin
public class UserAccountsController {
    private final ModelMapper modelMapper;

    @Autowired
    public UserAccountsController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * POST /api/user_account - Create a new user account.
     * @return UserAccount
     */
    @PostMapping()
    public ResponseEntity<UserAccount> post(@RequestBody UserAccountDTO userAccountDTO, @RequestHeader("Authorization") String headerAuth) {
        String token = headerAuth.substring(7);
        return null;
    }
}
