package com.ucm.ms.accounts.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity Class for user_loan table
 * 
 * 
 * @author Charvin Patel
 */



@Entity
@Table(	name = "user_loan")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "user_loan_id")
public class UserLoan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int user_loan_id;
	
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
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="account_number")
    @JsonBackReference
   // @JsonManagedReference
    UserAccount user_account;
    
    

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

	public int getUser_loan_id() {
		return user_loan_id;
	}

	public void setUser_loan_id(int user_loan_id) {
		this.user_loan_id = user_loan_id;
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

	public UserAccount getUser_account() {
		return user_account;
	}

	public void setUser_account(UserAccount user_account) {
		this.user_account = user_account;
	}
	
	
	
	

    
}
