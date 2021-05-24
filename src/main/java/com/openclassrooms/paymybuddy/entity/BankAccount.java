package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    @Column(name = "iban")
    private String IBAN;
    @Column(name = "account_number")
    private String accountNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User   user;

    public BankAccount() {}

    public BankAccount(int id, String IBAN, User user) {

        this.id   = id;
        this.IBAN = IBAN;
        this.user = user;
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
