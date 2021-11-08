package com.ucm.ms.accounts.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

public class RequestAccountTypeDto {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String type;

	@NotBlank
	private Boolean allowCredit;
	
	@NotBlank
	private BigDecimal creditLimit;
	
	@NotBlank
	private Boolean allowCards;
	
	@NotBlank
	private BigDecimal apr;

	@NotBlank
	private String perks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	

	public Boolean getAllowCredit() {
		return allowCredit;
	}

	public void setAllowCredit(Boolean allowCredit) {
		this.allowCredit = allowCredit;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Boolean getAllowCards() {
		return allowCards;
	}

	public void setAllowCards(Boolean allowCards) {
		this.allowCards = allowCards;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public String getPerks() {
		return perks;
	}

	public void setPerks(String perks) {
		this.perks = perks;
	}
	
	
	
	
	

}
