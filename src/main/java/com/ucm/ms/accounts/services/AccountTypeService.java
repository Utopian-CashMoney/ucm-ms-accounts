package com.ucm.ms.accounts.services;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucm.ms.accounts.dao.AccountTypeDAO;
import com.ucm.ms.accounts.dto.RequestAccountTypeDto;
import com.ucm.ms.accounts.entities.AccountType;

/**
 * Service class for account types
 * 
 * @author Josten Asercion (with much credit to Charvin Patel for reference)
 */
@Service
@Transactional
public class AccountTypeService {
	
	@Autowired
	AccountTypeDAO accountTypeDAO;

	/**
	 * @param accountTypeInfo The data from the forms to create an account type
	 */
	public void createAccountType(RequestAccountTypeDto accountTypeInfo) {

		AccountType accountType = new AccountType();

		// Can be CREDIT, DEBIT, or LOAN
		accountType.setType(accountTypeInfo.getType());

		accountType.setName(accountTypeInfo.getName());

		accountType.setPerks(accountTypeInfo.getPerks());

		switch (accountTypeInfo.getType()) {
		case "CREDIT":
			accountType.setAllowCredit(true);
			accountType.setCreditLimit(accountTypeInfo.getCreditLimit());
			accountType.setAllowCards(true);
			accountType.setApr(accountTypeInfo.getApr());
			break;
		case "DEBIT":
			accountType.setAllowCredit(false);
			accountType.setCreditLimit(BigDecimal.valueOf(0.00));
			accountType.setAllowCards(true);
			accountType.setApr(BigDecimal.valueOf(0.00));
			break;
		case "LOAN":
			accountType.setAllowCredit(true);
			accountType.setCreditLimit(accountTypeInfo.getCreditLimit());
			accountType.setAllowCards(false);
			accountType.setApr(accountTypeInfo.getApr());
			break;
		//If it gets to default, something went really wrong
		default:
			break;
		}
		
		accountTypeDAO.save(accountType);
	}
	
	/**
	 * @param id the id of the account type to modify
	 */
	public void editAccountType(Integer id, RequestAccountTypeDto accountTypeInfo) {
		AccountType accountType = accountTypeDAO.getAccountTypeById(id);
		accountType.setName(accountTypeInfo.getName());
		accountType.setPerks(accountTypeInfo.getPerks());
		
		if(accountType.getType().equals("CREDIT")||accountType.getType().equals("CREDIT")) {
			accountType.setCreditLimit(accountTypeInfo.getCreditLimit());
			accountType.setApr(accountTypeInfo.getApr());
		}
		
		accountTypeDAO.save(accountType);
	}
	
	/**
	 * @param id the id of the account type to delete
	 */
	public void deleteAccountType(Integer id) {
		accountTypeDAO.deleteById(id);
	
	}
}
