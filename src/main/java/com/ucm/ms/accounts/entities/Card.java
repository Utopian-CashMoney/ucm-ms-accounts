package com.ucm.ms.accounts.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card implements Serializable{
	private static final long serialVersionUID = -8717102373082913856L;

	// Data
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "exp")
	private LocalDateTime exp;

	@Column(name = "cvv")
	private String cvv;

	// Relationships
	@ManyToOne
	@JoinColumn(name = "account_number", referencedColumnName = "account_Number")
	private UserAccount userAccount;
	
	// Methods
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDateTime getExp() {
		return exp;
	}

	public void setExp(LocalDateTime exp) {
		this.exp = exp;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, accountNumber, cardNumber, exp, cvv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Card card = (Card) obj;
		return Objects.equals(id, card.id) && Objects.equals(accountNumber, card.accountNumber)
				&& Objects.equals(cardNumber, card.cardNumber) && Objects.equals(exp, card.exp)
				&& Objects.equals(cvv, card.cvv);
	}

}
