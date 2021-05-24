package com.openclassrooms.paymybuddy.entity;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private BankAccount bankAccount;

   @OneToMany(mappedBy = "user")
    private List<Friend> friendList;

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

        return balance;
    }

    public void setMoneyAvailable(Double balance) {

        this.balance = balance;
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

        return "User [id=" + id + ", email=" + email + ", lastName=" + lastName + ", firstName=" + firstName + ", password=" + password + ", moneyAvailable=" + balance + ", bankAccount=" + bankAccount  + ", friends=" + "]";
    }

}
