package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT t " +
                    "FROM Transaction t " +
                    "WHERE t.userAccount.user.id = :id")
    Page<Transaction> searchByUserID(@Param("id") Integer id, Pageable pageable);
}
