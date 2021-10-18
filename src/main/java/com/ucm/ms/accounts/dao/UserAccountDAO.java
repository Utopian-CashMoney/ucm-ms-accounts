package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.UserAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDAO extends JpaRepository<UserAccount, String> {
	
	@Query(value = "SELECT * FROM user_account WHERE user_id = :userId", nativeQuery = true)
	public Collection<UserAccount> getUserAccounts(int userId);
	
}
