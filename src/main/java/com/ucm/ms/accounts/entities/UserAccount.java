package com.ucm.ms.accounts.entities;

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
public class UserAccount implements Serializable {
	private static final long serialVersionUID = -7468301910750885913L;

	// Data
	@Id
	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "balance")
	private BigDecimal balance;

	// Relationships
	@ManyToOne
	@JoinColumn(name="users_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	// Methods
	public UserAccount() {
	}

	public UserAccount(String accountNumber, BigDecimal balance, Account account) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.account = account;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserAccount that = (UserAccount) o;
		return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(balance, that.balance) && Objects.equals(user, that.user) && Objects.equals(account, that.account);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, balance, user, account);
	}
}