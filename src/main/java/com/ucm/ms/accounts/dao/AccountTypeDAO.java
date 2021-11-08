package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AccountTypeDAO extends JpaRepository<AccountType, Integer> {
	/**
	 * Get the first 50 results, querying all data.
	 * 
	 * @return A collection of at most 50 Accounts.
	 */
	Collection<AccountType> findTop50ByOrderById();

	@Query(value = "SELECT * FROM account_type WHERE type = :type", nativeQuery = true)
	public Collection<AccountType> getLoans(String type);

	// public AccountType getIdByName(String name);

	public AccountType getAccountTypeByName(String name);

	@Query(value = "SELECT * FROM account_type WHERE type = 'CREDIT'", nativeQuery = true)
	public Collection<AccountType> getCreditCards();

	public AccountType getIdByName(String name);

	public AccountType getAprByName(String name);

}
