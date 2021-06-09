package com.openclassrooms.paymybuddy.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
public class User implements Serializable, UserDetails {


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

    @OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.ALL})
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Friend> friendList;

    private boolean enabled;

    private String role;


    public User(int id, String firstName, String lastName, String email, String password, Double moneyAvailable, BankAccount bankAccount, List<Friend> friendList, boolean enabled, String role) {

        this.id             = id;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.email          = email;
        this.password       = password;
        this.moneyAvailable = moneyAvailable;
        this.bankAccount    = bankAccount;
        this.friendList     = friendList;
        this.enabled        = enabled;
        this.role           = role;
    }


    public User() { }


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }


    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }


    public void setPassword(String password) {
        this.password = String.valueOf(new BCryptPasswordEncoder(Integer.parseInt(password)));
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

    public boolean isEnabled() {

        return enabled;
    }

    public void setEnabled(boolean enabled) {

        this.enabled = enabled;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    @Override
    public String toString() {

        return "User{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", moneyAvailable=" + moneyAvailable + ", bankAccount=" + bankAccount + ", friendList=" + friendList + ", enabled=" + enabled + ", role='" + role + '\'' + '}';
    }
}
