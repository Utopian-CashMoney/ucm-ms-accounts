package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.dao.UserAccountConfirmationDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.dto.RegisterUserAccountDTO;
import com.ucm.ms.accounts.dto.RegisterUserAccountRespDTO;
import com.ucm.ms.accounts.services.UserAccountRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user_account")
@CrossOrigin
public class UserAccountsController {
    private final UserAccountRegistration userAccountRegistration;

    @Autowired
    public UserAccountsController(UserAccountRegistration userAccountRegistration) {
        this.userAccountRegistration = userAccountRegistration;
    }

    /**
     * POST /api/user_account - Create a new user account.
     * @return UserAccount
     */
    @PostMapping()
    public ResponseEntity<RegisterUserAccountRespDTO> post(@RequestBody RegisterUserAccountDTO registerUserAccountDTO,
                                                           @RequestHeader("Authorization") String headerAuth) {
        String token = headerAuth.substring(7);
        return new ResponseEntity<>(userAccountRegistration.register(registerUserAccountDTO, token), HttpStatus.CREATED);
    }

    /**
     * GET /api/user_account/confirmation?token=...
     * @param modelAndView Page to return to the user.
     * @param confirmationToken Confirmation token.
     * @return Page for end user.
     */
    @GetMapping(path = "/confirmation")
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        if(userAccountRegistration.confirm(confirmationToken)) modelAndView.setViewName("emailActivated");
        else modelAndView.setViewName("emailLinkExpired");
        return modelAndView;
    }
}
