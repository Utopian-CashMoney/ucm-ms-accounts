package com.ucm.ms.accounts.services;

import com.ucm.ms.accounts.dao.DebitCardDAO;
import com.ucm.ms.accounts.entities.DebitCard;
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
public class CardSearch {
    private final DebitCardDAO debitCardDAO;

    /**
     * Uses constructor-based dependency injection.
     * @param debitCardDAO The Card's DAO
     */
    @Autowired
    public CardSearch(DebitCardDAO debitCardDAO) {
        this.debitCardDAO = debitCardDAO;
    }
    
    /**
	 * Get all Cards in DB
	 * 
	 * @return Collection of all cards
	 */
	public Collection<DebitCard> getAll() {
		return debitCardDAO.findAll();
	}
}
