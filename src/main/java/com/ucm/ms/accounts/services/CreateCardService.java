package com.ucm.ms.accounts.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ucm.lib.dao.UserRepository;
import com.ucm.lib.entities.User;
import com.ucm.ms.accounts.dao.AccountTypeDAO;
import com.ucm.ms.accounts.dao.CreditCardDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.dto.RequestAccountTypeDto;
import com.ucm.ms.accounts.entities.AccountType;
import com.ucm.ms.accounts.entities.CreditCard;
import com.ucm.ms.accounts.entities.UserAccount;


/**
 * Service Class for calling Jpa repository
 * 
 * 
 * @author Charvin Patel
 */


@Service
@Transactional
public class CreateCardService {
	
	@Autowired
	AccountTypeDAO createCardDao;
	
	@Autowired
	AccountTypeDAO accountTypeDao;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAccountDAO userAccountDao;
	
	@Autowired
	CreditCardDAO creditCardDao;

	
	/**
	 * 
	 * @param CreateCardDto
	 * @return void
	 * 
	 */
	
	public void createCard(RequestAccountTypeDto cardInfo) {
		
		AccountType createCard = new AccountType();
		
		
		createCard.setName(cardInfo.getName());
		createCard.setType(cardInfo.getType());
		
		// This is for type debit 
		createCard.setAllowCredit(false);
		
		// This is for type debit 
		createCard.setCreditLimit(BigDecimal.valueOf(0.00));
		
		// For debit cards related to checking account
		createCard.setAllowCards(true);
		
		// This is for type debit 
		createCard.setApr(BigDecimal.valueOf(0.00));
		
		createCard.setPerks(cardInfo.getPerks());
		
		if(cardInfo.getType().equals("CREDIT") || cardInfo.getType().equals("LOAN")) {
			createCard.setAllowCredit(true);
			createCard.setCreditLimit(cardInfo.getCreditLimit());
			createCard.setApr(cardInfo.getApr());
		}
		
		
		createCardDao.save(createCard);
		
	}
	
	
	
	
	// signup a creditcard for the user
	public void usercreditCardSignUp(int userId, String cardName) {


		// put a check here for unique account number


		Random rnd = new Random();
		int accountNumber = rnd.nextInt(999999999);
		
		
		UserAccount userAccount = new UserAccount();
		User user = userRepository.getUserById(userId);

		
		userAccount.setAccountNumber(String.valueOf(accountNumber));
		userAccount.setUser(user);
		userAccount.setAccountType(accountTypeDao.getIdByName(cardName));
		userAccount.setBalance(BigDecimal.valueOf(10000.00));
		userAccount.setActive(true);

		System.out.println("HEREEE: " + userAccount.getAccountNumber());

		// ALSO ADD DATA TO CREDIT_CARD TABLE HEREE AS WELL
		
		CreditCard creditCard = new CreditCard();
		AccountType accountType = accountTypeDao.getAprByName(cardName);
		
		Random cardNumberRand = new Random();
		String creditCardNumber = String.valueOf(cardNumberRand.nextInt(999999999));

		Random cvvRand = new Random();
		int cvvNumber = cvvRand.nextInt(999);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormatted = dateFormat.format(new Date());
		
		char charAt = dateFormatted.charAt(3);
		
		int charToInt = Character.getNumericValue(charAt) + 2; 
		char intToChar=(char)(charToInt + '0');   
		
		
		StringBuilder myName = new StringBuilder(dateFormatted);
		myName.setCharAt(3, intToChar);
		
		
	 
		
		String creditCardExpiryDateCalulation = myName.toString();
		//Date creditCardExpiryDate;
		LocalDate creditCardExpiryDate;
		
				
				
		//	creditCardExpiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(creditCardExpiryDateCalulation);
			creditCardExpiryDate = LocalDate.parse(creditCardExpiryDateCalulation);

			creditCard.setCardNumber(creditCardNumber);
			creditCard.setUserAccount(userAccount);
			creditCard.setInterestRate(accountType.getApr());
			creditCard.setCvv(cvvNumber);
			creditCard.setExpirationDate(creditCardExpiryDate);
			
			
			userAccountDao.save(userAccount);
			creditCardDao.save(creditCard);	
			
	}
	
}

