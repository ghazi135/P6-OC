package com.openclassrooms.paymybuddy.it;

import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.Assert.assertTrue;


@DataJpaTest
@Import(UserService.class)
public class UserServiceIT {


    @Autowired
    private UserService userService;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void injectedComponentsAreRightlySetUp() {

        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(testEntityManager);
    }


    @Test
    public void updateUserIT() {

        User user = new User(1, "ghazi", "bouzazi", "ghazi@gmail.com", "111", 1111.00, null, null);

        userService.saveUser(user);
        List<User> result = userService.findAll();
        Assertions.assertTrue(result.toString().contains("ghazi"));
        User user1 = new User(1, "ddddddd", "zzzzzzz", "gggg@ggggg", "777777", 1111.00, null, null);

        userService.updateUser(user.getId(), user1);

        Assertions.assertTrue(result.toString().contains("gggg@ggggg"));


    }

}
