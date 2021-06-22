package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer> {
}
