package com.ucm.ms.accounts.dto;

import javax.validation.constraints.NotNull;

public class RegisterUserAccountRespDTO {
    @NotNull
    private String accountNumber;
    @NotNull
    private String accountType;
    @NotNull
    private String accountName;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
