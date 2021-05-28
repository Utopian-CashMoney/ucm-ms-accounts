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
    @OneToOne(targetEntity = UserAccount.class)
    @JoinColumn(name="user_account_id", referencedColumnName = "account_number", nullable = false)
    private String userAccountID;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expires;

    public String getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(String userAccountID) {
        this.userAccountID = userAccountID;
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
        return Objects.equals(userAccountID, that.userAccountID) && Objects.equals(code, that.code) && Objects.equals(expires, that.expires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountID, code, expires);
    }
}
