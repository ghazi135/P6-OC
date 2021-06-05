package com.openclassrooms.paymybuddy.service;


import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.FriendRepositoy;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    @Autowired
    private UserRepository userRepository;

    @Mock
    private FriendRepositoy friendRepositoy;

    private UserService userService;

    private User   user;
    private Friend friend;

    @BeforeEach
    void setup() {

    }


}
