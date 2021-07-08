package com.ucm.ms.accounts.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A response object for a single Transaction. Does not contain the ID or its relationships.
 *
 * @author Joshua Podhola
 */
public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = 7225650055507280744L;

    private String accountNumber;
    private String reason;
    private BigDecimal amount;
    private String destination;
    private LocalDateTime timestamp;
    private String status;

    public TransactionDTO(String accountNumber, String reason, BigDecimal amount, String destination, LocalDateTime timestamp, String status) {
        this.accountNumber = accountNumber;
        this.reason = reason;
        this.amount = amount;
        this.destination = destination;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(reason, that.reason) && Objects.equals(amount, that.amount) && Objects.equals(destination, that.destination) && Objects.equals(timestamp, that.timestamp) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, reason, amount, destination, timestamp, status);
    }
}
