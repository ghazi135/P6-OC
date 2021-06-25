package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find user by email.
     *
     * @param email user email
     * @return useer by email
     */

    User findUsersByEmail(String email);

    /**
     * check if the user exist by this email.
     *
     * @param email user email
     */
    boolean existsByEmail(String email);

    /**
     * delete user by email
     *
     * @param email user email
     */
    void deleteUserByEmail(String email);
}
