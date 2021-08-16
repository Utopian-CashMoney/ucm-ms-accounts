package com.ucm.ms.accounts.controllers;

import com.ucm.ms.accounts.entities.DebitCard;
import com.ucm.ms.accounts.services.DebitCardSearch;
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
@RequestMapping("/debitcards")
public class DebitCardController {
	private final DebitCardSearch debitCardSearch;

	@Autowired
	public DebitCardController(DebitCardSearch debitCardSearch) {
		this.debitCardSearch = debitCardSearch;
	}

	/**
	 * GET /api/debitcards - Return all debit cards
	 * 
	 * @return All debit cards
	 */
	@GetMapping
	public ResponseEntity<Collection<DebitCard>> get() {
		try {
			Collection<DebitCard> cards = debitCardSearch.getAll();
			return new ResponseEntity<>(cards, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
