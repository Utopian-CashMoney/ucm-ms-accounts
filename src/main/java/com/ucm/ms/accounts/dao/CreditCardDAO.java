package com.ucm.ms.accounts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucm.ms.accounts.entities.CreditCard;

@Repository
public interface CreditCardDAO extends JpaRepository<CreditCard, String> {

	
	
	
}
