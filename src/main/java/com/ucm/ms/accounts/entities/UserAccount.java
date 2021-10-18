package com.ucm.ms.accounts.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucm.lib.entities.User;
import com.ucm.lib.entity.IVerifiableEntity;
//import com.ucm.ms.entity.AccountType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable, IVerifiableEntity {
	private static final long serialVersionUID = -7468301910750885913L;

	// Data
	@Id
	@Column(name = "account_number", nullable = false)
	private String accountNumber;

	@Column(name = "balance", nullable = false)
	private BigDecimal balance;

//	@Column(name="active", nullable = false)
//	private Boolean active;

	// Relationships
//	@ManyToOne(targetEntity = User.class)
//	@JoinColumn(name="users_id", referencedColumnName = "id", nullable = false)
//	@JsonManagedReference("UserAccountHasUser")
//	private User user;

//	@ManyToOne
//	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
//	@JsonManagedReference("UserAccountHasAccount")
//	private Account account;
	
//	@Id
//	private String accountNumber;
	
	
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_type_id")
    @JsonManagedReference
    @JsonIgnore
    private AccountType account_type;
    
//    @Column(name="balance")
//    BigDecimal balance;
    

	// Methods
	public UserAccount() {
	}

	public UserAccount(String accountNumber, BigDecimal balance, /* Account account ,*/ User user) {
		this.accountNumber = accountNumber;
		this.balance = balance;
//		this.account = account;
		this.user = user;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

//	public Account getAccount() {
//		return account;
//	}
//
//	public void setAccount(Account account) {
//		this.account = account;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public Boolean isActive() {
//		return active;
//	}
//
//	public void setActive(Boolean active) {
//		this.active = active;
//	}
	
	

	public AccountType getAccount_type() {
		return account_type;
	}

	public void setAccount_type(AccountType account_type) {
		this.account_type = account_type;
	}

//	public Boolean getActive() {
//		return active;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	/*	result = prime * result + ((account == null) ? 0 : account.hashCode());*/
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((account_type == null) ? 0 : account_type.hashCode());
//		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		/*if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;*/
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (account_type == null) {
			if (other.account_type != null)
				return false;
		} else if (!account_type.equals(other.account_type))
			return false;
//		if (active == null) {
//			if (other.active != null)
//				return false;
//		} else if (!active.equals(other.active))
//			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public Boolean isActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActive(Boolean active) {
		// TODO Auto-generated method stub
		
	}
	
	


	
}