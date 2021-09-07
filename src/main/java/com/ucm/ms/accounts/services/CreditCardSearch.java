package com.ucm.ms.accounts.services;

import com.ucm.ms.accounts.dao.CreditCardDAO;
import com.ucm.ms.accounts.entities.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

import java.util.Collection;

/**
 * Services designed to fetch collections of Cards
 */
@Service
@Transactional
public class CreditCardSearch {
    private final CreditCardDAO creditCardDAO;

    /**
     * Uses constructor-based dependency injection.
     * @param creditCardDAO The Card's DAO
     */
    @Autowired
    public CreditCardSearch(CreditCardDAO creditCardDAO) {
        this.creditCardDAO = creditCardDAO;
    }
    
    /**
	 * Get all Cards in DB
	 * 
	 * @return Collection of all cards
	 */
	public Collection<CreditCard> getAll() {
		return creditCardDAO.findAll();
	}
}
