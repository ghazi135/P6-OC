package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;

@Entity
@Table(name = "friend")
public class Friend {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_friend_id")
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
