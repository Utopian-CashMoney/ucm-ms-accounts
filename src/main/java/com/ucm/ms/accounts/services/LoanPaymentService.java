package com.ucm.ms.accounts.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.ucm.lib.dao.UserDAO;
import com.ucm.ms.accounts.dao.AccountTypeDAO;
import com.ucm.ms.accounts.dao.UserAccountActivityDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.dao.UserLoanDAO;
import com.ucm.ms.accounts.dto.RequestUserAccountDto;
import com.ucm.ms.accounts.entities.UserAccount;
import com.ucm.ms.accounts.entities.UserAccountActivity;

@Service
@Transactional
public class LoanPaymentService {

	@Autowired
	LoanTypeAdd loanTypeAdd;

	@Autowired
	UserLoanAdd userLoanAdd;

	@Autowired
	UserDAO userDao;

	@Autowired
	UserLoanDAO userLoanDAO;

	@Autowired
	UserAccountDAO userAccountDAO;

	@Autowired
	AccountTypeDAO accountTypeDAO;

	RequestUserAccountDto userAccountDto;

	@Autowired
	UserAccountActivityDAO userAccountActivityDao;

	public void loanPayment(int userId, double amount,
			 String payeeAccountNumber) {

		UserAccount userAccount = userAccountDAO.getUserAccount(userId, 9);
		UserAccount payeeAccount = userAccountDAO.getUserAccountByAccountNumber(payeeAccountNumber);

		Date todaysDate = new Date();
		todaysDate.getDate();
		Random rnd = new Random();
		int transactionId = rnd.nextInt(999999999);

		UserAccountActivity userAccountActivity = new UserAccountActivity();

		// If amount to be paid is greater then available balance or there is no
		// outstanding balance to be paid.
		if (amount > userAccount.getBalance().doubleValue() || amount > payeeAccount.getBalance().doubleValue()) {
			throw new Error("Not Enough Money in account to pay");
		}

		else if (amount <= userAccount.getBalance().doubleValue()
				&& amount <= payeeAccount.getBalance().doubleValue()) {

			// Subtracting checking account's balance after user pays loan and credit card.
			userAccount.setBalance(BigDecimal.valueOf(userAccount.getBalance().doubleValue() - amount));

			// Subtracting payees account balance since the amount is paid.
			payeeAccount.setBalance(BigDecimal.valueOf(payeeAccount.getBalance().doubleValue() - amount));

			userAccountActivity.setActivityId(transactionId);
			userAccountActivity.setDate(todaysDate);
			userAccountActivity
					.setDescription("made a payment to account" + " " + payeeAccount.getAccountType().getName() + " "
							+ payeeAccountNumber + " transaction #: " + transactionId);
			userAccountActivity.setType("");
			userAccountActivity.setAmount(BigDecimal.valueOf(amount));
			userAccountActivity.setPayeeAccountNumber(payeeAccountNumber);
			userAccountActivity.setUserAccount(userAccount);

			userAccountActivityDao.save(userAccountActivity);

		}

		userAccountDAO.save(userAccount);

	}

}
