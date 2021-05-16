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
	//TODO: Many-to-one with User

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

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, balance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserAccount userAccount = (UserAccount) obj;
		return Objects.equals(accountNumber, userAccount.accountNumber)
				&& Objects.equals(balance, userAccount.balance);
	}
}