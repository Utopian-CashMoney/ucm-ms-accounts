package com.ucm.ms.accounts.services;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucm.ms.accounts.dao.AccountTypeDAO;
import com.ucm.ms.accounts.dto.RequestAccountTypeDto;
import com.ucm.ms.accounts.entities.AccountType;


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
}

