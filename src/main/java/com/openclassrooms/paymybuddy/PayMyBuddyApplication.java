package com.openclassrooms.paymybuddy;

import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PayMyBuddyApplication {

    public static void main(String[] args) {

        SpringApplication.run(PayMyBuddyApplication.class, args);
    }

}
