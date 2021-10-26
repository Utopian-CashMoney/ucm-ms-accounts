package com.ucm.ms.accounts.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Entity Class for user_loan table
 * 
 * 
 * @author Charvin Patel, Josten Asercion
 */

@Entity
@Table(	name = "user_loan")
public class UserLoan implements Serializable{
	private static final long serialVersionUID = 1320241626135216678L;

	@Id
	@Column(name = "user_loan_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userLoanId;
	
    @Column(name = "salary")
	private int salary;
        
    @Column(name = "start_date")
	private LocalDate startDate;
    
    @Column(name = "is_accepted")
	private Boolean isAccepted;
    
    @Column(name="term")
    private String term;
    
    @Column(name = "status")
    private String status;
    
    @ManyToOne
	@JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false)
    @JsonManagedReference("LoanHasUserAccount")
    private UserAccount userAccount;
    
    public UserLoan() {
    }
    
	public UserLoan(int userLoanId, int salary, LocalDate startDate, Boolean isAccepted, String term, String status,
			UserAccount userAccount) {
		this.userLoanId = userLoanId;
		this.salary = salary;
		this.startDate = startDate;
		this.isAccepted = isAccepted;
		this.term = term;
		this.status = status;
		this.userAccount = userAccount;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}


	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public int getUserLoanId() {
		return userLoanId;
	}

	public void setUserLoanId(int userLoanId) {
		this.userLoanId = userLoanId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
	
	

    
}
