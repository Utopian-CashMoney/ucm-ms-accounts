package com.ucm.ms.accounts.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.ucm.lib.dao.UserDAO;
import com.ucm.lib.entities.User;
import com.ucm.ms.accounts.dao.AccountTypeDAO;
import com.ucm.ms.accounts.dao.UserAccountActivityDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.dao.UserLoanDAO;
import com.ucm.ms.accounts.dto.RequestAccountTypeDto;
import com.ucm.ms.accounts.dto.RequestUserAccountDto;
import com.ucm.ms.accounts.dto.RequestUserLoanSignupDto;
import com.ucm.ms.accounts.dto.ResponseLoanMonthlyPaymentDto;
import com.ucm.ms.accounts.entities.UserAccount;
import com.ucm.ms.accounts.entities.UserAccountActivity;
import com.ucm.ms.accounts.entities.UserLoan;
import com.ucm.ms.accounts.services.LoanSearch;
import com.ucm.ms.accounts.services.LoanTypeAdd;
import com.ucm.ms.accounts.services.UserLoanAdd;

/**
 * Controller Class for Handling API calls
 * 
 * 
 * @author Charvin Patel
 */


@RestController
@RequestMapping("/loans")
@CrossOrigin

public class LoansController {

	private final LoanSearch loanSearch;

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

	@Autowired
	private SpringTemplateEngine templateEngine;



	@Autowired
	public LoansController(LoanSearch loanSearch) {
		this.loanSearch = loanSearch;
	}

	/**
	 * GET /api/loans - Return all loans
	 * 
	 * @return All loans on offer
	 */
	/*
	@GetMapping("/all_loans")
	public ResponseEntity<Collection<AccountType>> get(@RequestParam String type) {
		try {
			Collection<AccountType> loans = loanSearch.getLoans(type);
			System.out.println("HEREEE");
			return new ResponseEntity<>(loans, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	*/
	
	@GetMapping
	public ResponseEntity<Collection<UserLoan>> get() {
		try {
			Collection<UserLoan> loans = loanSearch.getAll();
			return new ResponseEntity<>(loans, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * 
	 * @param salary(user), amount of loan, loan Term, loan interest rate.
	 * @return ResponseEntity
	 * 
	 */

	@PostMapping("/loansignup")
	public ResponseEntity<?> signupLoan(@RequestParam int userId, @RequestParam String loanName,
			@RequestParam int salary, @RequestParam int amount, @RequestParam int term,
			@RequestParam double interestRate) {

		DecimalFormat df = new DecimalFormat("###.##");

		int termInMonths = term * 12;

		ResponseLoanMonthlyPaymentDto loanDto = new ResponseLoanMonthlyPaymentDto();

		double monthlyPayments = loanTypeAdd.calculateMonthlyPayments(amount, term, interestRate);

		double totalPayments = loanTypeAdd.calculateTotalPayment(monthlyPayments, termInMonths);

		loanDto.setPayments(Double.valueOf(df.format((monthlyPayments))));
		loanDto.setTotalPayment(Double.valueOf(df.format((totalPayments))));

		return ResponseEntity.ok((new ResponseLoanMonthlyPaymentDto(loanDto.getPayments(), loanDto.getTotalPayment())));

	}

	/**
	 * 
	 * @param user id, loanrequest
	 * @return void
	 * 
	 */

	// WORKINGGGGGG

	@PostMapping("/loanSignupSuccess")
	public void signupLoanSuccess(@RequestParam int userId, @RequestBody RequestUserLoanSignupDto loanRequest) {

		// Before setting is_accpeted to true/loan accepeted... we need to
		// make sure that our customer makes atleast 50% of what their loan amount is.

		double acceptableIncome = loanRequest.getBalance().doubleValue() * 0.50;

		User toEmail = userDao.getEmailById(userId);

		String to = toEmail.getEmail();

		String from = "utopiacashmoney99@gmail.com";
		final String username = "utopiacashmoney99@gmail.com";
		final String password = "UtopiaBanking100?";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		Random rnd = new Random();
		int accountNumber = rnd.nextInt(999999999);

		if (loanRequest.getSalary() >= acceptableIncome) {

			try {

				User user = userDao.getUserById(userId);
				UserAccount userAccount = new UserAccount();
				userAccount.setAccountNumber(String.valueOf(accountNumber));
				userAccount.setUser(user);
			//	userAccount.setAccount_type(accountTypeDAO.getIdByName(loanRequest.getName()));
				
				userAccount.setAccountType(accountTypeDAO.getAccountTypeByName(loanRequest.getName()));
				userAccount.setActive(true);


				userAccount.setBalance(loanRequest.getBalance());

				userAccountDAO.save(userAccount);
				loanRequest.setIs_accepted(true);

				loanTypeAdd.signUpLoan(loanRequest.getBalance(), String.valueOf(accountNumber), loanRequest);

				// Mocking a delay for 10s when a loan manager tries to review the loan
				// application
				// and makes the decision, but these delays the navigation to next webpage
				//Thread.sleep(10 * 1000);

				UserLoan userLoan = new UserLoan();
				userLoan.setStatus("IN REVIEW");
				userLoanDAO.updateStatusByUserAccount(String.valueOf(accountNumber), userLoan.getStatus());

				// Mocking a delay for 10s when a loan manager tries to review the loan
				// application
				// and makes the decision, but these delays the navigation to next webpage
			    // Thread.sleep(10 * 1000);
				userLoan.setStatus("APPROVED");
				userLoanDAO.updateStatusByUserAccount(String.valueOf(accountNumber), userLoan.getStatus());

				try {
					message.setFrom(new InternetAddress(from));

					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

					Context context = new Context();
					String html = templateEngine.process("../templates/loanAccepted.html", context);

					message.setSubject("Loan Application Update!");
					message.setContent(html, "text/html");

					Transport.send(message);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else {
			try {
				UserLoan userLoan = new UserLoan();

				loanRequest.setIs_accepted(false);
				loanTypeAdd.signUpLoan(loanRequest.getBalance(), String.valueOf(accountNumber), loanRequest);

				
				// Mocking a delay for 10s when a loan manager tries to review the loan
				// application
				// and makes the decision, but these delays the navigation to next webpage
				//Thread.sleep(10 * 1000);
				userLoan.setStatus("IN REVIEW");
				userLoanDAO.updateStatusByUserAccount(String.valueOf(accountNumber), userLoan.getStatus());
				
				// Mocking a delay for 10s when a loan manager tries to review the loan
				// application
				// and makes the decision, but these delays the navigation to next webpage
				//Thread.sleep(10 * 1000);
				userLoan.setStatus("DECLINED");
				userLoanDAO.updateStatusByUserAccount(String.valueOf(accountNumber), userLoan.getStatus());


				
				message.setFrom(new InternetAddress(from));

				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				Context context = new Context();

				String html = templateEngine.process("../templates/loanRejected.html", context);

				message.setSubject("Loan Application Update!");
				message.setContent(html, "text/html");
				Transport.send(message);

			} catch (Exception e) {

			}

		}

	}

	/**
	 * 
	 * @param RequestCreateLoanDto
	 * @return void
	 * 
	 */

	// WORKINGGGGGGGGGGGGGGG

	@PostMapping("/createLoans")
	public void createLoans(@RequestBody RequestAccountTypeDto accountTypeDto) {

		loanTypeAdd.createLoan(accountTypeDto);

	}
	
	
	// WORKINGGG
	@GetMapping("/loan_status")
	public ResponseEntity<Collection<UserLoan>> loanStatus(@RequestParam String userId) {
		try {
			
			User user = userDao.getUserById(Integer.valueOf(userId));

			Collection<UserAccount> userAccount = userAccountDAO.getUserAccountByUserId(user.getId());
			Collection<UserLoan> ul = new ArrayList<>();
			
			for(UserAccount u : userAccount) {
				ul.addAll(userLoanDAO.getUserLoanByAccountNumber(u.getAccountNumber()));
				
			}
				
						
			return new ResponseEntity<>(ul, HttpStatus.OK);


			
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/submit_payment")
	public void createLoans(@RequestParam int userId, @RequestParam double amount, @RequestParam String payeeAccountNumber) {
		
		UserAccount userAccount = userAccountDAO.getUserAccount(userId, 9);	
		UserAccount payeeAccount = userAccountDAO.getUserAccountByAccountNumber(payeeAccountNumber);
		
		Date todaysDate = new Date();
		todaysDate.getDate();
		Random rnd = new Random();
		int transactionId = rnd.nextInt(999999999);
		
		UserAccountActivity userAccountActivity = new UserAccountActivity();
						
		
		// If amount to be paid is greater then available balance or there is no outstanding balance to be paid.
		if (amount > userAccount.getBalance().doubleValue() || amount > payeeAccount.getBalance().doubleValue()) {
			throw new Error("Not Enough Money in account to pay");
		}
				
		
		else if(amount <= userAccount.getBalance().doubleValue() && amount <= payeeAccount.getBalance().doubleValue()) {
			
		// Subtracting checking account's balance after user pays loan and credit card.
		userAccount.setBalance(BigDecimal.valueOf(userAccount.getBalance().doubleValue() - amount));
		
		// Subtracting payees account balance since the amount is paid.
		payeeAccount.setBalance(BigDecimal.valueOf(payeeAccount.getBalance().doubleValue() - amount));
		
		userAccountActivity.setActivityId(transactionId);
		userAccountActivity.setDate(todaysDate);
		userAccountActivity.setDescription("made a payment to account" + " " +  payeeAccount.getAccountType().getName() + " " + payeeAccountNumber + " transaction #: " + transactionId);
		userAccountActivity.setType("");
		userAccountActivity.setAmount(BigDecimal.valueOf(amount));
		userAccountActivity.setPayeeAccountNumber(payeeAccountNumber);
		userAccountActivity.setUserAccount(userAccount);
		
		userAccountActivityDao.save(userAccountActivity);
				
		
		}
		
		userAccountDAO.save(userAccount);

	}
	
	@GetMapping("/account_activity")
	public ResponseEntity<Collection<UserAccountActivity>> accountActivity(@RequestParam String payeeAccountNumber) {
		try {
			
			
//			UserAccountActivity accountActivity = new UserAccountActivity();
			Collection<UserAccountActivity> accountActivities = userAccountActivityDao.getUserAccountActivityByPayeeAccountNumber(payeeAccountNumber);
			
			for(UserAccountActivity u : accountActivities) {
				System.out.println(u.getPayeeAccountNumber());
			}
			
				
			return new ResponseEntity<>(accountActivities, HttpStatus.OK);	
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
