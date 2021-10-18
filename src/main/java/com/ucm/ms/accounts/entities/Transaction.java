//package com.ucm.ms.accounts.entities;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Entity
//@Table(name = "transaction")
//public class Transaction implements Serializable {
//    private static final long serialVersionUID = 6126350707693993458L;
//
//    //Data
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "amount", nullable = false)
//    private BigDecimal amount;
//
//    @Column(name = "timestamp", nullable = false)
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
//    private LocalDateTime timestamp;
//
//    @Column(name = "message", nullable = false)
//    private String message;
//
//    @Column(name = "status", nullable = false)
//    private String status;
//
//    @Column(name = "destination", nullable = false)
//    private String destination;
//
//    //Relationships
//    @ManyToOne
//    @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false)
//    @JsonManagedReference("TransactionHasUserAccount")
//    private UserAccount userAccount;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getDestination() {
//        return destination;
//    }
//
//    public void setDestination(String destination) {
//        this.destination = destination;
//    }
//
//    public UserAccount getUserAccount() {
//        return userAccount;
//    }
//
//    public void setUserAccount(UserAccount userAccount) {
//        this.userAccount = userAccount;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Transaction that = (Transaction) o;
//        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(timestamp, that.timestamp) && Objects.equals(message, that.message) && Objects.equals(status, that.status) && Objects.equals(destination, that.destination) && Objects.equals(userAccount, that.userAccount);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, amount, timestamp, message, status, destination, userAccount);
//    }
//}
