package com.ucm.ms.accounts.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ucm.ms.accounts.entities.UserAccount;
import com.ucm.ms.accounts.entities.UserLoan;

/**
 * Repository Class for Handling database calls
 * 
 * 
 * @author Charvin Patel
 */
	
	@Repository
	public interface UserLoanDAO extends JpaRepository<UserLoan, Integer> {
	    
		@Modifying
		@Query(value = "insert into user_loan (account_number, salary, start_date, is_accepted, term) VALUES (:account_number, :salary, :start_date, :is_accepted, :term)", nativeQuery = true)
		@Transactional
		public void signupUserLoan( @Param("account_number") String account_number, @Param("salary") int salary, @Param("start_date") LocalDate start_date, @Param("is_accepted") Boolean is_accepted, @Param("term") String term);
		
		
		@Modifying
		@Query(value = "update user_loan u set u.status = :status where u.account_number = :account_number", nativeQuery = true)
		@Transactional
		public void updateStatusByUserAccount( @Param ("account_number") String account_number, @Param("status") String status);
		
		
//		@Query(value = "select status from user_loan where account_number = :account_number", nativeQuery = true)
//		public UserLoan getStatusByUserAccount(@Param ("account_number") String account_number);
		
		@Query(value = "select * from user_loan where account_number = :account_number", nativeQuery = true)
		public Collection<UserLoan> getUserLoanByAccountNumber(@Param ("account_number") String account_number);
		
	}



