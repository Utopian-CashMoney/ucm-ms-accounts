package com.ucm.ms.accounts.services;

import com.ucm.ms.accounts.dao.TransactionDAO;
import com.ucm.ms.accounts.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionDAO transactionDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public Page<Transaction> searchTransactions(Integer userID, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "timestamp"));
        return transactionDAO.searchByUserID(userID, paging);
    }
}
