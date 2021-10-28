package com.ucm.ms.accounts.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ucm.ms.accounts.dao.UserLoanDAO;
import com.ucm.ms.accounts.entities.UserLoan;

/**
 * Service Class for calling user_loan repository
 * 
 * 
 * @author Charvin Patel
 */

@Service
@Transactional
public class UserLoanAdd {

	@Autowired
	private UserLoanDAO userLoanDAO;

	/**
	 * 
	 * @param userLoan
	 * @return UserLoan(Entity)
	 * 
	 */

	// This is just updating the current user if the user signs up for more then 1
	// loan
//	public UserLoan saveUserLoan(UserLoan userLoan) {
//		return userLoanDAO.save(userLoan);
//	}

	
	public void saveUserLoan( @Param("account_number") String accountNumber, @Param("salary") int salary, @Param("start_date") LocalDate startDate, @Param("is_accepted") Boolean isAccepted, @Param("term") String term) {
//		return userLoanDAO.save(userLoan);
		userLoanDAO.signupUserLoan(accountNumber, salary, startDate, isAccepted, term);
	}
	
	
	

}
