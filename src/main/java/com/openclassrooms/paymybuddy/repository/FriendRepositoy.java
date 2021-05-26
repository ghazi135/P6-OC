package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepositoy extends JpaRepository<Friend, Integer> {
    List<Friend> findFriendByUser_Email(String email);


}
