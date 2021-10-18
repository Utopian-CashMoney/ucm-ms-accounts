//package com.ucm.ms.accounts.services;
//
//import com.ucm.ms.accounts.dao.CardDAO;
//import com.ucm.ms.accounts.entities.Card;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import javax.transaction.Transactional;
//
//import java.util.Collection;
//
///**
// * Services designed to fetch collections of Cards
// */
//@Service
//@Transactional
//public class CardSearch {
//    private final CardDAO cardDAO;
//
//    /**
//     * Uses constructor-based dependency injection.
//     * @param cardDAO The Card's DAO
//     */
//    @Autowired
//    public CardSearch(CardDAO cardDAO) {
//        this.cardDAO = cardDAO;
//    }
//    
//    /**
//	 * Get all Cards in DB
//	 * 
//	 * @return Collection of all cards
//	 */
//	public Collection<Card> getAll() {
//		return cardDAO.findAll();
//	}
//}
