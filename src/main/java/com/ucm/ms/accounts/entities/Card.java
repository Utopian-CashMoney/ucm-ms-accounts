package com.ucm.ms.accounts.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.time.LocalDate;
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

	@Column(name = "card_number", nullable = false)
	private String cardNumber;

	@Column(name = "exp", nullable = false)
	private LocalDate exp;

	@Column(name = "cvv", nullable = false)
	private String cvv;

	// Relationships
	@ManyToOne
	@JoinColumn(name = "account_number", referencedColumnName = "account_Number", nullable = false)
	@JsonManagedReference("CardHasUserAccount")
	private UserAccount userAccount;
	
	// Methods
	public Card() {
	}

	public Card(String cardNumber, LocalDate exp, String cvv, UserAccount userAccount) {
		this.cardNumber = cardNumber;
		this.exp = exp;
		this.cvv = cvv;
		this.userAccount = userAccount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getExp() {
		return exp;
	}

	public void setExp(LocalDate exp) {
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
		return Objects.hash(id, cardNumber, exp, cvv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Card card = (Card) obj;
		return Objects.equals(id, card.id)
				&& Objects.equals(cardNumber, card.cardNumber) && Objects.equals(exp, card.exp)
				&& Objects.equals(cvv, card.cvv);
	}

}
