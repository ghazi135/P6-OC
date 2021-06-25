package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepositoy extends JpaRepository<Friend, Integer> {

    /**
     * find friends by user email
     *
     * @param email user email
     * @return List of friends
     */

    List<Friend> findFriendByUser_Email(String email);

    /**
     * find transaction of beneficiary and payer user
     *
     * @param userId user email
     * @param userFriendId user email
     * @return List of friends
     */
    Friend findFriendByUserIdAndUserFriendId(Integer userId, Integer userFriendId);

    /**
     * delete friend by user id and user friend id
     *
     * @param userId user id
     * @param userFriendId user friend id
     */
    void deleteFriendByUserIdAndUserFriendId(Integer userId, Integer userFriendId);
}
