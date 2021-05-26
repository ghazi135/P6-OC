package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Double amount;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beneficiary_user_id")
    private User beneficiaryUser;


    @OneToOne
    @JoinColumn(name = "payer_user_id")
    private User payerUser;


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Double getAmount() {

        return amount;
    }

    public void setAmount(Double amount) {

        this.amount = amount;
    }

    public User getBeneficiaryUser() {

        return beneficiaryUser;
    }

    public void setBeneficiaryUser(User beneficiaryUser) {

        this.beneficiaryUser = beneficiaryUser;
    }

    public User getPayerUser() {

        return payerUser;
    }

    public void setPayerUser(User payerUser) {

        this.payerUser = payerUser;
    }
}
