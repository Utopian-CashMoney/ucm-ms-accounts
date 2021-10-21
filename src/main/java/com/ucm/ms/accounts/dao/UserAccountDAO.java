package com.ucm.ms.accounts.dao;

import com.ucm.ms.accounts.entities.UserAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDAO extends JpaRepository<UserAccount, String> {
	
public UserAccount getUserIDByaccountNumber(String account_number);
	
	public Collection<UserAccount> getUserAccountByUserId(int user);
}
