package com.ucm.ms.accounts.controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucm.lib.dao.UserRepository;
import com.ucm.lib.entities.User;
import com.ucm.ms.accounts.dao.AccountTypeDAO;
import com.ucm.ms.accounts.dao.CreditCardDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.entities.AccountType;
import com.ucm.ms.accounts.entities.CreditCard;
import com.ucm.ms.accounts.entities.UserAccount;


@CrossOrigin
@RestController
@RequestMapping("/cards")
public class CardsController {
//	private final CardSearch cardSearch;
	
	@Autowired
	AccountTypeDAO accountTypeDao;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAccountDAO userAccountDao;
	
	@Autowired
	CreditCardDAO creditCardDao;
	

	@Autowired
//	public CardsController(CardSearch cardSearch) {
//		this.cardSearch = cardSearch;
//	}

	/**
	 * GET /api/cards - Return all cards
	 * 
	 * @return All cards
	 */
//	@GetMapping
//	public ResponseEntity<Collection<Card>> get() {
//		try {
//			Collection<Card> cards = cardSearch.getAll();
//			return new ResponseEntity<>(cards, HttpStatus.valueOf(200));
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@GetMapping(path = "get_credit_cards")
	public ResponseEntity<Collection<AccountType>> getCreditCards() {

		try {

			Collection<AccountType> creditCards = accountTypeDao.getCreditCards();
			System.out.println("INSIDE ACCOUNTS CARDS");

			return new ResponseEntity<>(creditCards, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@PostMapping(path = "/user_credit_card_signup")
	public void usercreditCardSignUp(@RequestParam int userId, @RequestParam String cardName) {


		// put a check here for unique account number
	
		


		Random rnd = new Random();
		int accountNumber = rnd.nextInt(999999999);
		
		
		UserAccount userAccount = new UserAccount();
		User user = userRepository.getUserById(userId);

		
		userAccount.setAccountNumber(String.valueOf(accountNumber));
		userAccount.setUser(user);
		userAccount.setAccount_type(accountTypeDao.getIdByName(cardName));
		userAccount.setBalance(BigDecimal.valueOf(10000.00));

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
		Date creditCardExpiryDate;
				
				
		try {
			creditCardExpiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(creditCardExpiryDateCalulation);
			creditCard.setCard_number(creditCardNumber);
			creditCard.setUserAccount(userAccount);
			creditCard.setInterestRate(accountType.getApr());
			creditCard.setCvv(cvvNumber);
			creditCard.setExpiryDate(creditCardExpiryDate);
			
			userAccountDao.save(userAccount);
			creditCardDao.save(creditCard);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}	
			
	}
	
	
	@GetMapping("/user_account")
	public ResponseEntity<Collection<UserAccount>> getUserAccounts(@RequestParam String userId) {

		int usersId = Integer.parseInt(userId);

		Collection<UserAccount> userAccount = userAccountDao.getUserAccounts(usersId);		
		

		return new ResponseEntity<>(userAccount, HttpStatus.valueOf(200));
	}
	
}
