package com.ucm.ms.accounts.controllers;

import com.ucm.lib.services.AuthenticationFacade;
import com.ucm.ms.accounts.dto.TransactionDTO;
import com.ucm.ms.accounts.services.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    private final TransactionService transactionService;
    private final AuthenticationFacade authenticationFacade;

    public TransactionController(TransactionService transactionService, AuthenticationFacade authenticationFacade) {
        this.transactionService = transactionService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping()
    @RolesAllowed({"ROLE_USER"})
    public Page<TransactionDTO> get(@RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return transactionService.searchTransactions(authenticationFacade.getUserInfo().getId(), page, pageSize);
    }
}
