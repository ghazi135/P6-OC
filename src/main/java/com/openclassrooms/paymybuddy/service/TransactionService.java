package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@Log4j2
public class TransactionService {

    /**
     * @see TransactionRepository
     */

    @Autowired
    private final TransactionRepository transactionRepository;

    /**
     * @see UserRepository
     */
    @Autowired
    private final UserRepository userRepository;

    /**
     * @see AccountService
     */
    @Autowired
    private final AccountService accountService;

    private Transaction newTransaction;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, AccountService accountService) {

        this.transactionRepository = transactionRepository;
        this.userRepository        = userRepository;
        this.accountService        = accountService;
    }

    /**
     * find all transactions between users
     *
     * @return all transactions
     */

    public List<Transaction> findAllTransactions() {

        log.info("<-----------find all transactions");

        return transactionRepository.findAll();
    }

    /**
     * find transactions by principal user
     *
     * @param user
     *         iban of bank account
     *
     * @return list of transactions
     */
    public List<Transaction> findTransactionsOfPrincipalUser(User user) {

        log.info("<-----------find transactions od principal user");

        List<Transaction> payerTransactionList = transactionRepository.findTransactionByPayerUser_Email(user.getEmail());
        List<Transaction> beneficiaryTransactionList = transactionRepository.findTransactionByBeneficiaryUser_Email(user
                .getEmail());

        return Stream.of(payerTransactionList, beneficiaryTransactionList)
                     .flatMap(Collection::stream)
                     .collect(Collectors.toList());
    }

    /**
     * send money to your contact
     *
     * @param emailSender
     *         email of user sender
     * @param amount
     *         amount to send
     * @param EmailReceiver
     *         email of user receiver
     * @param description
     *         description of transfer
     */
    public void sendMoney(String emailSender, Double amount, String EmailReceiver, String description) {

        User userSender   = userRepository.findUsersByEmail(emailSender);
        User userReceiver = userRepository.findUsersByEmail(EmailReceiver);

        if (userSender.getMoneyAvailable() - ((amount) + (amount * 0.005)) < 0) {
            log.error("<-----------Solde insuffisant");

            throw new IllegalStateException("Solde insuffisant");
        } else if (userReceiver == null) {
            log.error("<-----------user Receiver introuvable");

            throw new IllegalStateException("user Receiver introuvable");
        } else {
            log.info("<-----------send money to your contact");

            userSender.setMoneyAvailable(userSender.getMoneyAvailable() - ((amount) + (amount * 0.005)));
            userReceiver.setMoneyAvailable(userReceiver.getMoneyAvailable() + amount);
            userRepository.save(userSender);
            userRepository.save(userReceiver);
            newTransaction = new Transaction();
            newTransaction.setAmount(amount);
            newTransaction.setPayerUser(userSender);
            newTransaction.setBeneficiaryUser(userReceiver);
            newTransaction.setDescription(description);
            transactionRepository.save(newTransaction);
        }

    }


    /**
     * get back your money from application to your bank account
     *
     * @param emailUser
     *         email of user sender
     * @param amount
     *         email of user sender
     */
    public void getBackMoneyOnMyBankAccount(String emailUser, Double amount) {

        User user = userRepository.findUsersByEmail(emailUser);
        if (user.getMoneyAvailable() < amount) {
            log.error("<-----------Solde insuffisant");

            throw new IllegalStateException("Solde insuffisant");
        } else {
            log.info("<-----------get back your money on your bank account");

            user.setMoneyAvailable(user.getMoneyAvailable() - amount);
            userRepository.save(user);
        }
    }

    /**
     * recharge application by bank account
     *
     * @param emailUser
     *         email user
     * @param amount
     *         amount to recharge
     * @param iban
     *         iban of bank account
     */
    public void rechargeApplicationAccount(String emailUser, Double amount, String iban) {

        User user = userRepository.findUsersByEmail(emailUser);
        if (accountService.isAccorded(iban)) {
            log.info("<-----------recharge your application account");

            user.setMoneyAvailable(user.getMoneyAvailable() + amount);
        } else {
            log.error("<-----------rechargement non accordé");

            throw new IllegalStateException("rechargement non accordé");

        }
    }


}
