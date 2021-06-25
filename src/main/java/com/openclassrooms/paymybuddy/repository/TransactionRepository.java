package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    /**
     * find transaction of payer user
     *
     * @param email user email
     * @return List of transactions
     */
    List<Transaction> findTransactionByPayerUser_Email(String email);

    /**
     * find transaction of beneficiary user
     *
     * @param email user email
     * @return List of transactions
     */
    List<Transaction> findTransactionByBeneficiaryUser_Email(String email);


}
