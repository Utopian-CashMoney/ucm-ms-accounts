package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AccountTypeDAO extends JpaRepository<AccountType, Integer> {
    /**
     * Get the first 50 results, querying all data.
     * @return A collection of at most 50 Accounts.
     */
    Collection<AccountType> findTop50ByOrderById();
}
