package com.openclassrooms.paymybuddy.it;

import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.FriendRepositoy;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@DataJpaTest
@Import(UserService.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIT {


    @Autowired
    FriendRepositoy friendRepositoy;
    @Autowired
    private UserService    userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void injectedComponentsAreRightlySetUp() {

        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(testEntityManager);
    }

    @BeforeEach
    void setup() {

        userRepository.deleteAll();
        friendRepositoy.deleteAll();

    }


    @Test
    public void updateUserIT() {

        User user = new User(1, "ghazi", "bouzazi", "ghazi@gmail.com", "111", 1111.00, null, null);

        userService.saveUser(user);
        List<User> result = userService.findAll();
        Assertions.assertTrue(result.toString().contains("ghazi"));
        User user1 = new User();
        user1.setEmail("gggg@ggggg");
        user1.setPassword("88888");
        user1.setFirstName("aaaa");
        user1.setLastName("vvvvv");

        userService.updateUser(user.getId(), user1);

        Assertions.assertTrue(userRepository.findById(1).get().getLastName().contains("vvvvv"));


    }

    @Test
    public void deleteUserIT() {

        User user = new User(1, "ghazi", "bouzazi", "ghazi@gmail.com", "111", 1111.00, null, null);

        userService.saveUser(user);
        List<User> result = userService.findAll();
        Assertions.assertTrue(result.toString().contains("ghazi"));

        userService.deleteUserByEmail(user.getEmail());

        Assertions.assertFalse(userRepository.findById(1).isPresent());


    }


    @Test
    public void addAndDeleteAndFindAllFriendIT() {

        List<Friend> friendList = new ArrayList<>();
        User         user       = new User(1, "ghazi", "bouzazi", "ghazi@gmail.com", "111", 1111.00, null, friendList);
        userService.saveUser(user);
        List<User> result = userService.findAll();
        assertTrue(result.toString().contains("ghazi"));
        User user1 = new User(2, "ddddddd", "zzzzzzz", "gggg@ggggg", "777777", 1111.00, null, null);
        userService.saveUser(user1);
        //Add Friend
        userService.addFriend(user.getId(), user1.getId());
        assertTrue(userService.findFriendByPrincipalUserEmail(user.getEmail()).toString().contains("ddddddd"));
        //Find All Friends
        assertTrue(userService.findAllFriends().toString().contains("ddddddd"));

        //DELTE Friend
        userService.deleteFriend(user.getId(), user1.getId());
        assertFalse(friendRepositoy.findById(1).isPresent());
    }


}
