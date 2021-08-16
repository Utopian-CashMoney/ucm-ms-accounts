package com.ucm.ms.accounts.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A response object for a single Transaction. Does not contain the ID or its relationships.
 *
 * @author Joshua Podhola, Josten Asercion
 */
public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = 7225650055507280744L;

    private String accountNumber;
    private BigDecimal amount;
    private String name;
    private LocalDateTime timestamp;
    private Boolean isProcessed;
    private Boolean isCancelled;

    public TransactionDTO(String accountNumber, BigDecimal amount, String name, LocalDateTime timestamp, Boolean isProcessed, Boolean isCancelled) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.name = name;
        this.timestamp = timestamp;
        this.isProcessed = isProcessed;
        this.isCancelled = isCancelled;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDestination() {
        return name;
    }

    public void setDestination(String name) {
        this.name = name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public Boolean getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(amount, that.amount) && Objects.equals(name, that.name) && Objects.equals(timestamp, that.timestamp) && Objects.equals(isProcessed, that.isProcessed) && Objects.equals(isCancelled, that.isCancelled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, amount, name, timestamp, isProcessed, isCancelled);
    }
}
