package com.openclassrooms.paymybuddy.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_friends")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Friend implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id" )
    @JsonIgnore
    private User user;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_friend_id")
    @JsonIgnoreProperties("friendList")
    private User userFriend;


    public Friend(Integer id, User user, User userFriend) {

        this.id         = id;
        this.user       = user;
        this.userFriend = userFriend;
    }

    public Friend() {


    }

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
