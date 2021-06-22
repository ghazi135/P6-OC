package com.openclassrooms.paymybuddy.service;


import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.FriendRepositoy;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FriendRepositoy friendRepositoy;


    private UserService userService;

    private User        user;
    private Friend      friend;
    private BankAccount bankAccount;

    @BeforeEach
    void setup() {

        userService = new UserService(userRepository, friendRepositoy);
    }

    @Test
    public void findAllUsers() {

        User         user1      = null;
        List<Friend> friendList = new ArrayList<>();
        bankAccount = new BankAccount(1, "gg4444", "111111", user);
        user        = new User(1, "ghazi", "bouzazi", "ghazi@gmail.com", "111", 1111.00, bankAccount, friendList,true,"USER");
        BankAccount bankAccount1 = new BankAccount(2, "gg4444", "111111", user1);

        user1 = new User(2, "aaaa", "bbbb", "gggggg@gmail.com", "111eeeee", 11.00, bankAccount1, friendList,true,"USER");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        when(userRepository.findAll()).thenReturn(userList);

        assertThat(userService.findAll().toString(), containsString("ghazi"));

    }

    @Test
    public void findByUserId() {

        List<Friend> friendList = new ArrayList<>();
        bankAccount = new BankAccount(1, "gg4444", "111111", user);
        user        = new User(1, "ghazi", "bouzazi", "ghazi@gmail.com", "111", 1111.00, bankAccount, friendList,true,"USER");

        when(userRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(user));

        assertThat(userService.findById(anyInt()).toString(), containsString("ghazi"));

    }

    @Test
    public void findByUserEmail() {

        List<Friend> friendList = new ArrayList<>();
        bankAccount = new BankAccount(1, "gg4444", "111111", user);
        user        = new User(1, "ghazi", "bouzazi", "ghazi@gmail.com", "111", 1111.00, bankAccount, friendList,true,"USER");

        when(userRepository.findUsersByEmail(anyString())).thenReturn(user);

        assertThat(userService.findByEmail(anyString()).toString(), containsString("ghazi"));

    }



    @Test
    void SaveFriend() {

        user = new User();
        User   userFriend = new User();
        Friend friend     = new Friend(1, user, userFriend);
        when(friendRepositoy.save(any(Friend.class))).thenReturn(friend);
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.ofNullable(user));
        when(userRepository.findById(userFriend.getId())).thenReturn(java.util.Optional.of(userFriend));
        userService.addFriend(user.getId(), userFriend.getId());
        verify(friendRepositoy).save(any(Friend.class));
    }

    @Test
    void deleteFriend() {

        doNothing().when(friendRepositoy).deleteFriendByUserIdAndUserFriendId(1, 2);
        userService.deleteFriend(1, 2);
        verify(friendRepositoy).deleteFriendByUserIdAndUserFriendId(1, 2);
    }

    @Test
    void deleteUser() {

        doNothing().when(userRepository).deleteUserByEmail("test");
        userService.deleteUserByEmail("test");
        verify(userRepository).deleteUserByEmail("test");
    }

    @Test
    void SaveUser() {

        List<Friend> friendList = new ArrayList<>();
        bankAccount = new BankAccount(1, "gg4444", "111111", user);
        user        = new User(1, "ghazi", "bouzazi", "ghazi@gmail.com", "111", 1111.00, bankAccount, friendList,true,"USER");

        when(userRepository.save(user)).thenReturn(user);
        userService.saveUser(user);
        verify(userRepository).save(user);
    }


}
