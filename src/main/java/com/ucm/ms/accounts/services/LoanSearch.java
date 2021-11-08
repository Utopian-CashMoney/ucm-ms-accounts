package com.ucm.ms.accounts.services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucm.ms.accounts.dao.UserLoanDAO;
import com.ucm.ms.accounts.entities.AccountType;
import com.ucm.ms.accounts.entities.UserLoan;

@Service
@Transactional
public class LoanSearch {
    private final UserLoanDAO userLoanDAO;
	
//    private final AccountTypeDAO accountTypeDao;
   
    
    @Autowired
    public LoanSearch(UserLoanDAO userLoanDAO) {
        this.userLoanDAO = userLoanDAO;
    }

    /*
    public Collection<AccountType> getLoans(String type) {
    	
        return accountTypeDao.getLoans(type);
    }
    */
    
    /**
	 * Get all Loans in DB
	 * 
	 * @return Collection of all loans
	 */
	public Collection<UserLoan> getAll() {
		return userLoanDAO.findAll();
	}
    
	/**
	 * 
	 * @param name
	 * @return user_id(int)
	 * 
	 */
//    public int LoanId(String name) {
//    	 return loanTypeDAO.getIdByName(name);
//    }
    
}
