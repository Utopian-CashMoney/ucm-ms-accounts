package com.ucm.ms.accounts.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user_account_activity")
public class UserAccountActivity {
	
	@Id
	@Column(name = "activity_id", nullable = false)
	private int activityId;


	@Column(name="date", nullable = false)
	private Date date;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@Column(name="type", nullable = false)
	private String type;
	
	@Column(name="amount", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "payee_account_number", nullable = false)
	private String payeeAccountNumber;
	
	
	@ManyToOne
    @JoinColumn(name="account_number", nullable=false)
	@JsonIgnore
    private UserAccount userAccount;
	
	
	


	public int getActivityId() {
		return activityId;
	}


	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}


	public UserAccount getUserAccount() {
		return userAccount;
	}


	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	


	public String getPayeeAccountNumber() {
		return payeeAccountNumber;
	}


	public void setPayeeAccountNumber(String payeeAccountNumber) {
		this.payeeAccountNumber = payeeAccountNumber;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activityId;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((payeeAccountNumber == null) ? 0 : payeeAccountNumber.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userAccount == null) ? 0 : userAccount.hashCode());
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
		UserAccountActivity other = (UserAccountActivity) obj;
		if (activityId != other.activityId)
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (payeeAccountNumber == null) {
			if (other.payeeAccountNumber != null)
				return false;
		} else if (!payeeAccountNumber.equals(other.payeeAccountNumber))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userAccount == null) {
			if (other.userAccount != null)
				return false;
		} else if (!userAccount.equals(other.userAccount))
			return false;
		return true;
	}


	
	

}
