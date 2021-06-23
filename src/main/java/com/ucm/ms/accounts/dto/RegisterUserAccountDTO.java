package com.ucm.ms.accounts.dto;

import javax.validation.constraints.NotNull;

public class RegisterUserAccountDTO {
    @NotNull
    private Integer accountID;

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }
}
