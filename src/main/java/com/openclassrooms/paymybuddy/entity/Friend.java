package com.openclassrooms.paymybuddy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_friends")
public class Friend implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"friendList", "bankAccount", "moneyAvailable", "password"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_friend_id")
    @JsonIgnoreProperties({"friendList", "bankAccount", "password", "moneyAvailable"})

    private User userFriend;


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public User getUserFriend() {

        return userFriend;
    }

    public void setUserFriend(User userFriend) {

        this.userFriend = userFriend;
    }
}
