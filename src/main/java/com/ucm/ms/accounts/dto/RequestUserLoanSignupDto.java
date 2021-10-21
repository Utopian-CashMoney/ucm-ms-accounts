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
	private LocalDate start_date;
	
	@NotBlank
	private Boolean is_accepted;
	
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



	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public Boolean getIs_accepted() {
		return is_accepted;
	}

	public void setIs_accepted(Boolean is_accepted) {
		this.is_accepted = is_accepted;
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
