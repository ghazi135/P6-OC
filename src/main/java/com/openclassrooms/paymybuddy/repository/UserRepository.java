package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUsersByEmail(String email);

    boolean existsByEmail(String email);

    void deleteUserByEmail(String email);
}
