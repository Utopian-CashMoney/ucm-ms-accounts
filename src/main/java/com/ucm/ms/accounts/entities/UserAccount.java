package com.ucm.ms.accounts.entities;

import java.io.Serializable;
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
	private Integer balance;

	// Relationships
	@ManyToOne
	@JoinColumn(name = "users_id", referencedColumnName = "id")
	private Integer usersId;

	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Integer accountId;

	// Methods
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, usersId, accountId, balance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserAccount userAccount = (UserAccount) obj;
		return Objects.equals(accountNumber, userAccount.accountNumber) && Objects.equals(usersId, userAccount.usersId)
				&& Objects.equals(accountId, userAccount.accountId) && Objects.equals(balance, userAccount.balance);
	}
}