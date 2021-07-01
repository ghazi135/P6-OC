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

    /**
     * @see UserRepository
     */
    @Autowired
    private final UserRepository userRepository;

    /**
     * @see FriendRepositoy
     */
    @Autowired
    private final FriendRepositoy friendRepositoy;

    @Autowired
    public UserService(UserRepository userRepository, FriendRepositoy friendRepositoy) {

        this.userRepository  = userRepository;
        this.friendRepositoy = friendRepositoy;
    }

    /**
     * Find all users.
     *
     * @return User list
     */
    public List<User> findAll() {
        log.info("<-----------find all users ");
        return userRepository.findAll();
    }

    /**
     * Find user by id
     *
     * @param id User id
     * @return User by id
     */
    public Optional<User> findById(int id) {
        log.info("<-----------find user by id ");
        return userRepository.findById(id);
    }

    /**
     * Find friend all friendships
     *
     * @return all relations
     */
    public List<Friend> findAllFriends() {
        log.info("<-----------find all friendships");

        return friendRepositoy.findAll();
    }


    /**
     * Find friend by user email.
     *
     * @param email User email
     * @return friends by user email
     */
    public List<Friend> findFriendByPrincipalUserEmail(String email) {
        log.info("<-----------find Friend By Principal User Email");

        List<Friend> friendList =friendRepositoy.findFriendByUser_Email(email);
        friendList.removeIf(friend -> friend.getUserFriend() == friend.getUser());
        return friendList;
    }

    /**
     * sive user to the database
     *
     * @param  user to save
     */
    public void saveUser(User user) {

        List<Friend> friendList = new ArrayList<>();

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setMoneyAvailable(100.00);
        user.setRole("ROLE_USER");
        user.setBankAccount(null);
        user.setFriendList(friendList);
        user.setEnabled(true);
        userRepository.save(user);
        log.info("<-----------save user");

    }

    /**
     * delete user by email
     *
     * @param email User email
     */
    public void deleteUserByEmail(String email) {
        log.info("<-----------delete user by email");

        userRepository.deleteUserByEmail(email);
    }

    /**
     * find user by email
     *
     * @param email User email
     * @return user by his email
     */
    public User findByEmail(String email) {
        log.info("<-----------find user by email");

        return userRepository.findUsersByEmail(email);
    }

    /**
     * find users excepts friends
     *
     * @param email user email
     * @return users excepts friends list
     */
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

    /**
     * update user
     *
     * @param id User id to update
     * @param userUpdated to replace User
     */
    public void updateUser(Integer id, User userUpdated) {

        Optional<User> user = userRepository.findById(id);
        user.get().setFirstName(userUpdated.getFirstName());
        user.get().setLastName(userUpdated.getLastName());
        user.get().setEmail(userUpdated.getEmail());
        user.get().setPassword(userUpdated.getPassword());
        log.info("<-----------update user by id");

        userRepository.save(user.get());
    }

    /**
     * add user to contact list
     *
     * @param idUser of principal uer
     * @param idUserFriend contact of principal user
     */
    public void addFriend(Integer idUser, Integer idUserFriend) {

        Optional<User> user       = userRepository.findById(idUser);
        Optional<User> userFriend = userRepository.findById(idUserFriend);
        Friend         friend2    = new Friend();
        friend2.setUser(user.get());
        friend2.setUserFriend(userFriend.get());
        friendRepositoy.save(friend2);

    }

    /**
     * delete user from contact list
     *
     * @param userId of principal uer
     * @param idFriend contact of principal user
     */
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
