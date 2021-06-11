package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.FriendRepositoy;
import com.openclassrooms.paymybuddy.repository.UserRepository;
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

        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {

        return userRepository.findById(id);
    }

    public List<Friend> findAllFriends() {

        return friendRepositoy.findAll();
    }


    public List<Friend> findFriendByPrincipalUserEmail(String email) {

        return friendRepositoy.findFriendByUser_Email(email);
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
    }

    public void deleteUserByEmail(String email) {

        userRepository.deleteUserByEmail(email);
    }

    public User findByEmail(String email) {

        return userRepository.findUsersByEmail(email);
    }

    public List<User> usersExeptFriends(String email) {

        List<User> userList = userRepository.findAll();
        User       user     = userRepository.findUsersByEmail(email);

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

        Friend friend = friendRepositoy.findFriendByUserIdAndUserFriendId(userId, idFriend);
        friendRepositoy.deleteFriendByUserIdAndUserFriendId(userId, idFriend);


    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return userRepository.findUsersByEmail(s);
    }
}
