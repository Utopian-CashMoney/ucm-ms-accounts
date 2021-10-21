package com.ucm.ms.accounts.services;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucm.ms.accounts.dao.AccountTypeDAO;
import com.ucm.ms.accounts.dto.RequestAccountTypeDto;
import com.ucm.ms.accounts.dto.RequestUserAccountDto;
import com.ucm.ms.accounts.dto.RequestUserLoanSignupDto;
import com.ucm.ms.accounts.entities.AccountType;
import com.ucm.ms.accounts.entities.UserAccount;
import com.ucm.ms.accounts.entities.UserLoan;


/**
 * Service Class for calling loan repository
 * 
 * 
 * @author Charvin Patel
 */

@Service
@Transactional
public class LoanTypeAdd {

	@Autowired
	private LoanSearch loanSearch;

//	@Autowired
//	private LoanTypeDAO loanTypeDAO;


	@Autowired
	UserLoanAdd userLoanAdd;
	
	@Autowired
	AccountTypeDAO accountTypeDao;

	/**
	 * 
	 * @param loan
	 * @return Loan(Entity)
	 * 
	 */

//	public LoanType saveLoan(LoanType loantype) {
//		return loanTypeDAO.save(loantype);
//	}



	/**
	 * 
	 * @param userId, loanRequest(body)
	 * @return void
	 * 
	 */
	public void signUpLoan(BigDecimal balance, String account_number, RequestUserLoanSignupDto loanTypeRequest) {

		if(loanTypeRequest.getIs_accepted() == true) {
			
			userLoanAdd.saveUserLoan(account_number, loanTypeRequest.getSalary(), loanTypeRequest.getStart_date().now(), true, loanTypeRequest.getTerm());

		}
		else {
		userLoanAdd.saveUserLoan(account_number, loanTypeRequest.getSalary(), loanTypeRequest.getStart_date().now(), false, loanTypeRequest.getTerm());
		}
	}



	/**
	 * 
	 * @param amount of the loan, term of the loan, interest rate of the loan
	 * @return double
	 * 
	 * */

	public double calculateMonthlyPayments(int amount, int term, double interestRate) {

		interestRate /= 100.0;

		double monthlyRate = interestRate / 12.0;


		int termInMonths = term * 12;

		double monthlyPayment = 
				(amount*monthlyRate) / 
				(1-Math.pow(1+monthlyRate, -termInMonths));


		return monthlyPayment;
	}

	
	/**
	 * 
	 * @param monthly Payments, term in months
	 * @return double
	 * 
	 */
	
	public double calculateTotalPayment(double monthlyPayment, int termInMonths) {

		double totalPayment = monthlyPayment * termInMonths;
		System.out.println("Total Amount to be paid: " + totalPayment);


		return totalPayment;
	}
	
	
	public void createLoan(RequestAccountTypeDto accountTypeDto) {
		AccountType accountType = new AccountType();
		
//		accountType.setMaxAmount(accountTypeDto.getMax_amount());
//		accountType.setName(accountTypeDto.getName());
//		accountType.setInterestRate(accountTypeDto.getInterest_rate());
//		accountType.setTerm(accountTypeDto.getTerm());
		
//		System.out.println("Name: " + accountTypeDto.getName() + " Type: " + accountTypeDto.getType() + " AllowCredit: " + accountTypeDto.getAllowCredit() + " Credit Limit: " + accountTypeDto.getCreditLimit() + " AllowCard: " + accountTypeDto.getAllowCards() + " Apr: " + accountTypeDto.getApr() + " Perks: " +  accountTypeDto.getPerks());
		
		accountType.setName(accountTypeDto.getName());
		accountType.setType("LOAN");
		accountType.setAllowCredit(true);
//		accountType.setCreditLimit(accountTypeDto.getCreditLimit());
		accountType.setCreditLimit(accountTypeDto.getCredit_limit());
		accountType.setAllowCards(true);
		accountType.setApr(accountTypeDto.getApr());
		accountType.setPerks(accountTypeDto.getPerks());
		
		System.out.println("Name: " + accountTypeDto.getName() + " Type: " + accountType.getType() + " AllowCredit: " + accountType.getAllowCredit() + " Credit Limit: " + accountTypeDto.getCredit_limit() + " AllowCard: " + accountType.getAllowCards() + " Apr: " + accountTypeDto.getApr() + " Perks: " +  accountTypeDto.getPerks());
		
		
		accountTypeDao.save(accountType);
	}



}
