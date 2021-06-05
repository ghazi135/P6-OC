package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.FriendRepositoy;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepositoy friendRepositoy;

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {

        return userRepository.findById(id);
    }

    public List<Friend> firndAllFriends() {

        return friendRepositoy.findAll();
    }


    public List<Friend> findFriendByPrincipalUserEmail(String email) {

        return friendRepositoy.findFriendByUser_Email(email);
    }

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public void deleteUserByEmail(String email) {

        userRepository.deleteUserByEmail(email);
    }

    public void updateUser(Integer id, User userUpdated) {

        Optional<User> user = userRepository.findById(id);
        user.get().setFirstName(userUpdated.getFirstName());
        user.get().setLastName(userUpdated.getLastName());
        user.get().setEmail(userUpdated.getEmail());
        user.get().setPassword(userUpdated.getPassword());

        userRepository.save(user.get());
    }


    public void deleteFriend(Integer userId, Integer idFriend) {

        friendRepositoy.deleteFriendByUserIdAndUserFriendId(userId, idFriend);
    }


}
