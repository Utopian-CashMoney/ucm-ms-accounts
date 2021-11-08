package com.ucm.ms.accounts.dto;

import java.io.Serializable;


/**
 * ResponseLoanMonthlyPaymentDto class needed 
 * to calculate montly payments and total payments
 * of the loan
 * 
 * @author Charvin Patel
 */

public class ResponseLoanMonthlyPaymentDto implements Serializable {

	private static final long serialVersionUID = -4382170919176415708L;
	
	double payments;
	double totalPayment;
	
	public ResponseLoanMonthlyPaymentDto() {};
	
	public ResponseLoanMonthlyPaymentDto(double payments, double totalPayment) {
		this.payments = payments;
		this.totalPayment = totalPayment;
	}

	public double getPayments() {
		return payments;
	}

	public void setPayments(double payments) {
		this.payments = payments;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(payments);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseLoanMonthlyPaymentDto other = (ResponseLoanMonthlyPaymentDto) obj;
		if (Double.doubleToLongBits(payments) != Double.doubleToLongBits(other.payments))
			return false;
		if (Double.doubleToLongBits(totalPayment) != Double.doubleToLongBits(other.totalPayment))
			return false;
		return true;
	}
	
	
	
	

}
