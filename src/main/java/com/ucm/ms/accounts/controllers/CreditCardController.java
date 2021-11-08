package com.ucm.ms.accounts.controllers;

import java.util.Collection;

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
import com.ucm.ms.accounts.dao.AccountTypeDAO;
import com.ucm.ms.accounts.dao.CreditCardDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import com.ucm.ms.accounts.entities.AccountType;
import com.ucm.ms.accounts.entities.CreditCard;
import com.ucm.ms.accounts.services.CreateCardService;
import com.ucm.ms.accounts.services.CreditCardSearch;

@CrossOrigin
@RestController
@RequestMapping("/creditcards")
public class CreditCardController {
	private final CreditCardSearch creditCardSearch;
	
	
	@Autowired
	AccountTypeDAO accountTypeDao;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAccountDAO userAccountDao;
	
	@Autowired
	CreditCardDAO creditCardDao;
	
	@Autowired
	CreateCardService createCardService;
	

	@Autowired
	public CreditCardController(CreditCardSearch creditCardSearch) {
		this.creditCardSearch = creditCardSearch;
	}

	
		/**
		 * GET /api/creditcards - Return all credit cards
		 * 
		 * @return All credit cards
		 */
	
	
		@GetMapping(path="get_credit_cards")
		public ResponseEntity<Collection<CreditCard>> getCreditCardOnOffer() {
			try {
				Collection<CreditCard> cards = creditCardSearch.getAll();
				return new ResponseEntity<>(cards, HttpStatus.valueOf(200));
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	
		
		
		@GetMapping(path = "credit_card_on_offer")
		public ResponseEntity<Collection<AccountType>> getCreditCards() {

			try {

				Collection<AccountType> creditCards = accountTypeDao.getCreditCards();
				return new ResponseEntity<>(creditCards, HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		
		
		@PostMapping(path = "/user_credit_card_signup")
		public void usercreditCardSignUp(@RequestParam int userId, @RequestParam String cardName) {
			createCardService.usercreditCardSignUp(userId, cardName);

		}
		
		
		
	}

	

