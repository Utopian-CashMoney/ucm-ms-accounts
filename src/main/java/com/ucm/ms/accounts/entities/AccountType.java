package com.ucm.ms.accounts.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "account_type")
public class AccountType implements Serializable {
    private static final long serialVersionUID = -6183304311165530152L;

    //Data
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    //ENUM: ('CREDIT', 'DEBIT', 'LOAN')
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "allow_credit", nullable = false)
    private Boolean allowCredit;

    @Column(name = "credit_limit", nullable = false)
    private BigDecimal creditLimit;

    @Column(name = "allow_cards", nullable = false)
    private Boolean allowCards;
    
    @Column(name = "apr")
    private BigDecimal apr;
    
    @Column(name = "perks")
    private String perks;

    //Relationships
    //None


    /**
     * No-arg constructor for Account.
     * @author Joshua Podhola
     */
    public AccountType() {
    }


    /**
     * Constructor for Account
     * @author Josten Asercion
     * @param name Account name
     * @param type Account type: DEBIT, CREDIT, or LOAN
     * @param allowCredit Is the balance allowed to go negative? (Overdraft)
     * @param creditLimit How negative may the balance go?
     * @param allowCards Is this account allowed to have cards?
     * @param apr The annual percentage rate (if applicable)
     * @param perks Text description of the benefits of the account type
     */
    public AccountType(String name, String type, Boolean allowCredit, BigDecimal creditLimit, Boolean allowCards, BigDecimal apr, String perks) {
        this.name = name;
        this.type = type;
        this.allowCredit = allowCredit;
        this.creditLimit = creditLimit;
        this.allowCards = allowCards;
        this.apr = apr;
        this.perks = perks;
    }

    /**
     * Value constructor for Account (using Float for creditLimit and apr)
     * @author Joshua Podhola, Josten Asercion
     * @param name Account name
     * @param type Account type: DEBIT or CREDIT
     * @param allowCredit Is the balance allowed to go negative? (Overdraft)
     * @param creditLimit How negative may the balance go? (as Float)
     * @param allowCards Is this account allowed to have cards?
     * @param apr The annual percentage rate (if applicable)
     * @param perks Text description of the benefits of the account type
     */
    public AccountType(String name, String type, Boolean allowCredit, float creditLimit, Boolean allowCards, float apr, String perks) {
        this.name = name;
        this.type = type;
        this.allowCredit = allowCredit;
        this.creditLimit = new BigDecimal(creditLimit);
        this.allowCards = allowCards;
        this.apr = new BigDecimal(apr);
        this.perks = perks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAllowCredit() {
        return allowCredit;
    }

    public void setAllowCredit(Boolean allowCredit) {
        this.allowCredit = allowCredit;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Boolean getAllowCards() {
        return allowCards;
    }

    public void setAllowCards(Boolean allowCards) {
        this.allowCards = allowCards;
    }

    public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public String getPerks() {
		return perks;
	}

	public void setPerks(String perks) {
		this.perks = perks;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType account = (AccountType) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(type, account.type) && Objects.equals(allowCredit, account.allowCredit) && Objects.equals(creditLimit, account.creditLimit) && Objects.equals(allowCards, account.allowCards) && Objects.equals(apr, account.apr) && Objects.equals(perks, account.perks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, allowCredit, creditLimit, allowCards, apr, perks);
    }
}
