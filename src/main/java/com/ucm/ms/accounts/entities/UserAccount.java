package com.ucm.ms.accounts.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucm.lib.entities.User;
import com.ucm.lib.entity.IVerifiableEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Column(name="active", nullable = false)
	private Boolean active;
	
	// Relationships
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
	@JsonManagedReference("UserAccountHasUser")
	private User user;

	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	@JsonManagedReference("UserAccountHasAccount")
	private AccountType accountType;

	// Methods
	public UserAccount() {
	}

	public UserAccount(String accountNumber, BigDecimal balance, AccountType account, User user) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = account;
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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType account) {
		this.accountType = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserAccount that = (UserAccount) o;
		return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(balance, that.balance) && Objects.equals(active, that.active) && Objects.equals(user, that.user) && Objects.equals(accountType, that.accountType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, balance, active, user, accountType);
	}
}