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
	private static final long serialVersionUID = -3347095771105124076L;

	// Data
	@Id
	@Column(name = "card_number", nullable = false)
	private String cardNumber;

	@Column(name = "expiration_date", nullable = false)
	private LocalDate expirationDate;
	
	@Column(name = "interest_rate", nullable = false)
	private BigDecimal interestRate;
	
	@Column(name = "cvv", nullable = false)
	private Integer cvv;

	// Relationships
	@ManyToOne
	@JoinColumn(name = "account_number", referencedColumnName = "account_Number", nullable = false)
	@JsonManagedReference("CardHasUserAccount")
	private UserAccount userAccount;
	
	// Methods
	public CreditCard() {
	}

	public CreditCard(String cardNumber, LocalDate expirationDate, BigDecimal interestRate, Integer cvv, UserAccount userAccount) {
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
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

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, expirationDate, interestRate, cvv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		CreditCard card = (CreditCard) obj;
		return Objects.equals(cardNumber, card.cardNumber) && Objects.equals(expirationDate, card.expirationDate) &&
				Objects.equals(interestRate, card.interestRate)&& Objects.equals(cvv, card.cvv);
	}
}
