package com.ucm.ms.accounts.dto;
//package com.ss.ucm.ms.loans.dto;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import javax.validation.constraints.NotBlank;
//
///**
// * RequestSignupDto class needed to hold the user signup details
// * the user enters via signup page which then gets added into
// * the database
// * 
// * @author Charvin Patel
// */
//
//
//public class RequestLoanTypeSignupDto {
//
//	@NotBlank
//	private int id;
//	
//	@NotBlank
//	private int salary;
//
//	@NotBlank
//	private String name;
//
//	@NotBlank
//	private BigDecimal balance;
//
//	@NotBlank
//	private LocalDate startDate;
//	
//	@NotBlank
//	private Boolean is_accepted;
//	
//	
//	@NotBlank
//    enum term
//    {
//        ONE, TWO, FIVE;
//    }
//	
//	public RequestLoanTypeSignupDto() {};
//
//
//	public RequestLoanTypeSignupDto(String name){
//		this.name = name;
//	}
//	
//	
//	public int getSalary() {
//		return salary;
//	}
//
//
//	public void setSalary(int salary) {
//		this.salary = salary;
//	}
//
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public BigDecimal getBalance() {
//		return balance;
//	}
//
//	public void setBalance(BigDecimal balance) {
//		this.balance = balance;
//	}
//
//	public LocalDate getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(LocalDate startDate) {
//		this.startDate = startDate;
//	}
//
//	public Boolean getIs_accepted() {
//		return is_accepted;
//	}
//
//	public void setIs_accepted(Boolean is_accepted) {
//		this.is_accepted = is_accepted;
//	}
//	
//	
//	
//
//
//
//}
//
//
