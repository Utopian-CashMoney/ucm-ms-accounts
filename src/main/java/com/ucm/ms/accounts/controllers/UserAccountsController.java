package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.dao.UserAccountConfirmationDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.dto.RegisterUserAccountDTO;
import com.ucm.ms.accounts.dto.RegisterUserAccountRespDTO;
import com.ucm.ms.accounts.entities.UserAccount;
import com.ucm.ms.accounts.entities.UserAccountConfirmation;
import com.ucm.ms.accounts.services.UserAccountRegistration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Calendar;

@RestController
@RequestMapping("/user_account")
@CrossOrigin
public class UserAccountsController {
    private final ModelMapper modelMapper;
    private final UserAccountRegistration userAccountRegistration;
    private final UserAccountConfirmationDAO userAccountConfirmationDAO;
    private final UserAccountDAO userAccountDAO;

    @Autowired
    public UserAccountsController(ModelMapper modelMapper, UserAccountRegistration userAccountRegistration, UserAccountConfirmationDAO userAccountConfirmationDAO, UserAccountDAO userAccountDAO) {
        this.modelMapper = modelMapper;
        this.userAccountRegistration = userAccountRegistration;
        this.userAccountConfirmationDAO = userAccountConfirmationDAO;
        this.userAccountDAO = userAccountDAO;
    }

    /**
     * POST /api/user_account - Create a new user account.
     * @return UserAccount
     */
    @PostMapping()
    public ResponseEntity<RegisterUserAccountRespDTO> post(@RequestBody RegisterUserAccountDTO registerUserAccountDTO, @RequestHeader("Authorization") String headerAuth) {
        String token = headerAuth.substring(7);
        return new ResponseEntity<>(userAccountRegistration.register(registerUserAccountDTO, token), HttpStatus.CREATED);
    }

    @GetMapping(path = "/confirmation")
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
        UserAccountConfirmation userAccountConfirmation = userAccountConfirmationDAO.findFirstByCode(confirmationToken);

        if (userAccountConfirmation.getExpires().isAfter(LocalDateTime.now())) {
            modelAndView.setViewName("emailLinkExpired");
        }
        else {
            UserAccount userAccount = userAccountConfirmation.getUserAccount();
            userAccount.setActive(true);
            userAccountDAO.save(userAccount);
            modelAndView.setViewName("emailActivated");
        }
        return modelAndView;
    }
}
