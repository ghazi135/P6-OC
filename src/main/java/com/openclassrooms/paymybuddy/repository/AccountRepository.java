package com.openclassrooms.paymybuddy.repository;


import com.openclassrooms.paymybuddy.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount,Integer> {

}
