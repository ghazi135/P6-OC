package com.openclassrooms.paymybuddy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "user")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double moneyAvailable;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.ALL})
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Friend> friendList;

    public User(int id, String firstName, String lastName, String email, String password, Double moneyAvailable, BankAccount bankAccount, List<Friend> friendList) {

        this.id             = id;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.email          = email;
        this.password       = password;
        this.moneyAvailable = moneyAvailable;
        this.bankAccount    = bankAccount;
        this.friendList     = friendList;
    }

    public User() {


    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Double getMoneyAvailable() {

        return moneyAvailable;
    }

    public void setMoneyAvailable(Double moneyAvailable) {

        this.moneyAvailable = moneyAvailable;
    }

    public BankAccount getBankAccount() {

        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {

        this.bankAccount = bankAccount;
    }

    public List<Friend> getFriendList() {

        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {

        this.friendList = friendList;
    }

    @Override
    public String toString() {

        return "User [id=" + id + ", email=" + email + ", lastName=" + lastName + ", firstName=" + firstName + ", password=" + password + ", moneyAvailable=" + moneyAvailable + ", bankAccount=" + bankAccount + ", friends=" + "]";
    }

}
