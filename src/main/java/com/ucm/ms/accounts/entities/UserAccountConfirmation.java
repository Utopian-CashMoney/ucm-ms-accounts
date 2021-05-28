package com.ucm.ms.accounts.entities;

import com.ucm.lib.entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name="user_account_confirmation")
public class UserAccountConfirmation implements Serializable {
    private static final long serialVersionUID = 2237038858049091645L;

    @Id
    private String userAccountId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expires;

    @OneToOne
    @JoinColumn(name="user_account_id", referencedColumnName = "account_number")
    @MapsId
    private UserAccount userAccount;

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountConfirmation that = (UserAccountConfirmation) o;
        return Objects.equals(userAccountId, that.userAccountId) && Objects.equals(code, that.code) && Objects.equals(expires, that.expires) && Objects.equals(userAccount, that.userAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountId, code, expires, userAccount);
    }
}
