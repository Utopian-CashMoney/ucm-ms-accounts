package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.entities.Transaction;
import com.ucm.ms.accounts.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public Page<Transaction> get(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return transactionService.searchTransactions(1, page, pageSize);
    }
}
