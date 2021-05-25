package com.ucm.ms.accounts.services;

import com.ucm.lib.services.JwtUtil;
import com.ucm.ms.accounts.dto.RegisterUserAccountDTO;
import com.ucm.ms.accounts.entities.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAccountRegistrationTest {

    @Autowired
    UserAccountRegistration userAccountRegistration;

    @Autowired
    JwtUtil jwtUtil;

    @Test
    void registerTest() {
        RegisterUserAccountDTO registerUserAccountDTO = new RegisterUserAccountDTO();
        registerUserAccountDTO.setAccountID(1);
        //Known good token for data.sql's user with ID=1. Because these tokens expire, this likely isn't going to work in the future.
        //TODO: Come up with a better way to unit test this. -JP
//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTYyMTkxNjI0MCwiZXhwIjoxNjIxOTg2OTQwfQ.hIQDNPJhocBjt94z4C6nbZm91be5ljTrGaYtIbgiGz5TiMRlzFGqp9kG1ibGfuY9tP4xhqfmnt8x5YBtN7M3og";
//        UserAccount userAccount = userAccountRegistration.register(registerUserAccountDTO, token);
//        assertNotNull(userAccount);
//        assertNotNull(userAccount.getAccountNumber());
//        assertNotNull(userAccount.getUser());
//        assertNotNull(userAccount.getAccount());
    }
}