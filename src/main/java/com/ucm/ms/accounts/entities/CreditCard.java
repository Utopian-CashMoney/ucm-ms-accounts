package com.ucm.ms.accounts.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard implements Serializable{
	private static final long serialVersionUID = -8717102373082913856L;

	// Data
	@Column(name = "card_number", nullable = false)
	private String cardNumber;

	@Column(name = "expiry_date", nullable = false)
	private LocalDate expiryDate;
	
	@Column(name = "interest_rate", nullable = false)
	private BigDecimal interestRate;
	
	@Column(name = "cvv", nullable = false)
	private String cvv;

	// Relationships
	@ManyToOne
	@JoinColumn(name = "account_number", referencedColumnName = "account_Number", nullable = false)
	@JsonManagedReference("CardHasUserAccount")
	private UserAccount userAccount;
	
	// Methods
	public CreditCard() {
	}

	public CreditCard(String cardNumber, LocalDate expiryDate, BigDecimal interestRate, String cvv, UserAccount userAccount) {
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.interestRate = interestRate;
		this.cvv = cvv;
		this.userAccount = userAccount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExp(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, expiryDate, interestRate, cvv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		CreditCard card = (CreditCard) obj;
		return Objects.equals(cardNumber, card.cardNumber) && Objects.equals(expiryDate, card.expiryDate) &&
				Objects.equals(interestRate, card.interestRate)&& Objects.equals(cvv, card.cvv);
	}
}
