package com.ucm.ms.accounts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class RequestUserLoanSignupDto {

	
	@NotBlank
	private int id;
	
	@NotBlank
	private int salary;

	@NotBlank
	private LocalDate startDate;
	
	@NotBlank
	private Boolean isAccepted;
	
	@NotBlank
	private String term;
	
	@NotBlank
	private String name;
	
	
	@NotBlank
	private BigDecimal balance;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}



	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStart_date(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Boolean getIs_accepted() {
		return isAccepted;
	}

	public void setIs_accepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}


	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	
	
	
}
