package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository    userRepository;


    public List<BankAccount> findAllAccounts() {

        return accountRepository.findAll();
    }

    public Optional<BankAccount> findAccountById(int id) {


        return accountRepository.findById(id);
    }

    public BankAccount findAccountByEmail(String email) {

        return accountRepository.findBankAccountByUserEmail(email);
    }

    public void saveBankAccount(Integer idUser, BankAccount bankAccount) {

        bankAccount.setUser(userRepository.findById(idUser).get());
        accountRepository.save(bankAccount);
    }

    public void deleteAccount(Integer id) {

        accountRepository.deleteById(id);

    }
}
