package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.UserAccount;
import com.ucm.ms.accounts.entities.UserAccountActivity;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserAccountActivityDAO extends JpaRepository<UserAccountActivity, Integer> {
	
//	public UserAccount getUserIDByaccountNumber(String account_number);
//	
//	public Collection<UserAccount> getUserAccountByUserId(int user);
//	
//	
//	// Used to get all types of accounts a user holds
//	@Query(value = "SELECT * FROM user_account WHERE user_id = :userId", nativeQuery = true)
//	public Collection<UserAccount> getUserAccounts(int userId);
//	
//	// Used to get just the 1 "Debit" account a user holds
//	@Query(value = "SELECT * FROM user_account WHERE user_id = :userId AND account_type_id = :accountTypeId", nativeQuery = true)
//	public UserAccount getUserAccount(int userId, int accountTypeId);
//	
//	
//	// getting payee account 
//	public UserAccount getUserAccountByAccountNumber(String accountNumber);
	
	public Collection<UserAccountActivity> getUserAccountActivityByPayeeAccountNumber(String payeeAccountNumber);
	
	
	
	
}
