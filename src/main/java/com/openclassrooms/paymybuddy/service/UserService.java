package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.FriendRepositoy;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final FriendRepositoy friendRepositoy;

    @Autowired
    public UserService(UserRepository userRepository, FriendRepositoy friendRepositoy) {

        this.userRepository  = userRepository;
        this.friendRepositoy = friendRepositoy;
    }

    public List<User> findAll() {
        log.info("<-----------find all users ");
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        log.info("<-----------find user by id ");
        return userRepository.findById(id);
    }

    public List<Friend> findAllFriends() {
        log.info("<-----------find all friendships");

        return friendRepositoy.findAll();
    }


    public List<Friend> findFriendByPrincipalUserEmail(String email) {
        log.info("<-----------find Friend By Principal User Email");

        List<Friend> friendList =friendRepositoy.findFriendByUser_Email(email);
        friendList.removeIf(friend -> friend.getUserFriend() == friend.getUser());
        return friendList;
    }

    public void saveUser(User user) {

        List<Friend> friendList = new ArrayList<>();

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setMoneyAvailable(0.00);
        user.setRole("ROLE_USER");
        user.setBankAccount(null);
        user.setFriendList(friendList);
        user.setEnabled(true);
        userRepository.save(user);
        log.info("<-----------save user");

    }

    public void deleteUserByEmail(String email) {
        log.info("<-----------delete user by email");

        userRepository.deleteUserByEmail(email);
    }

    public User findByEmail(String email) {
        log.info("<-----------find user by email");

        return userRepository.findUsersByEmail(email);
    }

    public List<User> usersExceptFriends(String email) {
        log.info("<-----------find all users except friends");

        List<User> userList = userRepository.findAll();
        User       user     = userRepository.findUsersByEmail(email);
        userList.remove(findByEmail(email));
        for (Friend friend : user.getFriendList()) {
            userList.remove(friend.getUserFriend());
        }
        return userList;
    }

    public void updateUser(Integer id, User userUpdated) {

        Optional<User> user = userRepository.findById(id);
        user.get().setFirstName(userUpdated.getFirstName());
        user.get().setLastName(userUpdated.getLastName());
        user.get().setEmail(userUpdated.getEmail());
        user.get().setPassword(userUpdated.getPassword());
        log.info("<-----------update user by id");

        userRepository.save(user.get());
    }

    public void addFriend(Integer idUser, Integer idUserFriend) {

        Optional<User> user       = userRepository.findById(idUser);
        Optional<User> userFriend = userRepository.findById(idUserFriend);
        Friend         friend2    = new Friend();
        friend2.setUser(user.get());
        friend2.setUserFriend(userFriend.get());
        friendRepositoy.save(friend2);

    }

    public void deleteFriend(Integer userId, Integer idFriend) {
        log.info("<-----------delete friend by user id and friend id");

        Friend friend = friendRepositoy.findFriendByUserIdAndUserFriendId(userId, idFriend);
        friendRepositoy.deleteFriendByUserIdAndUserFriendId(userId, idFriend);


    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return userRepository.findUsersByEmail(s);
    }
}
