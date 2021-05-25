package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.dto.UserAccountDTO;
import com.ucm.ms.accounts.entities.UserAccount;
import com.ucm.ms.accounts.services.UserAccountRegistration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_account")
@CrossOrigin
public class UserAccountsController {
    private final ModelMapper modelMapper;
    private final UserAccountRegistration userAccountRegistration;

    @Autowired
    public UserAccountsController(ModelMapper modelMapper, UserAccountRegistration userAccountRegistration) {
        this.modelMapper = modelMapper;
        this.userAccountRegistration = userAccountRegistration;
    }

    /**
     * POST /api/user_account - Create a new user account.
     * @return UserAccount
     */
    @PostMapping()
    public ResponseEntity<UserAccount> post(@RequestBody UserAccountDTO userAccountDTO, @RequestHeader("Authorization") String headerAuth) {
        String token = headerAuth.substring(7);
        return new ResponseEntity<>(userAccountRegistration.register(userAccountDTO, token), HttpStatus.CREATED);
    }
}
