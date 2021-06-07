package com.openclassrooms.paymybuddy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bank_account")
@JsonIgnoreProperties({"friendList"})

public class BankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "iban")
    private String IBAN;

    @Column(name = "account_number")
    private String accountNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"friendList", "bankAccount", "password", "moneyAvailable"})
    private User user;

    public BankAccount(int id, String IBAN, String accountNumber, User user) {

        this.id            = id;
        this.IBAN          = IBAN;
        this.accountNumber = accountNumber;
        this.user          = user;
    }

    public BankAccount() {


    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getIBAN() {

        return IBAN;
    }

    public void setIBAN(String IBAN) {

        this.IBAN = IBAN;
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }
}
