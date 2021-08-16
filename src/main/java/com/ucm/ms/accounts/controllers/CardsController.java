package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.entities.CreditCard;
import com.ucm.ms.accounts.services.CardSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/cards")
public class CardsController {
	private final CardSearch cardSearch;

	@Autowired
	public CardsController(CardSearch cardSearch) {
		this.cardSearch = cardSearch;
	}

	/**
	 * GET /api/cards - Return all cards
	 * 
	 * @return All cards
	 */
	@GetMapping
	public ResponseEntity<Collection<CreditCard>> get() {
		try {
			Collection<CreditCard> cards = cardSearch.getAll();
			return new ResponseEntity<>(cards, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
