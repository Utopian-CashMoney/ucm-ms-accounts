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
    private String status;

    public TransactionDTO(String accountNumber, BigDecimal amount, String name, LocalDateTime timestamp, String status) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.name = name;
        this.timestamp = timestamp;
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(amount, that.amount) && Objects.equals(name, that.name) && Objects.equals(timestamp, that.timestamp) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, amount, name, timestamp, status);
    }
}
