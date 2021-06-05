USE
    `paymybuddy`;
CREATE TABLE `user`
(
    `id`         INT AUTO_INCREMENT NOT NULL,
    `first_name` VARCHAR(30)        NOT NULL,
    `last_name`  VARCHAR(40)        NOT NULL,
    `email`      VARCHAR(40)        NOT NULL,
    `balance`    DECIMAL(10, 2)     NOT NULL,
    `password`   VARCHAR(1000)      NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `bank_account`
(
    `id`             INT AUTO_INCREMENT NOT NULL,
    `account_number` INT                NOT NULL,
    `iban`           VARCHAR(34)        NOT NULL,
    `user_id`        INT                NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `user_friends`
(
    `id`             INT AUTO_INCREMENT NOT NULL,
    `user_id`        INT                NOT NULL,
    `user_friend_id` INT                NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `transaction`
(
    `id`                  INT AUTO_INCREMENT NOT NULL,
    `description`         VARCHAR(1000)      NOT NULL,
    `amount`              DECIMAL(10, 2)     NOT NULL,
    `beneficiary_user_id` INT,
    `payer_user_id`       INT,
    PRIMARY KEY (`id`)
);

ALTER TABLE `user_friends`
    ADD CONSTRAINT `user_user_friends_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `user_friends`
    ADD CONSTRAINT `user_user_friends_fk1`
        FOREIGN KEY (`user_friend_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `bank_account`
    ADD CONSTRAINT `user_bank_account_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;



ALTER TABLE `transaction`
    ADD CONSTRAINT `user_transaction_fk`
        FOREIGN KEY (`payer_user_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `transaction`
    ADD CONSTRAINT `user_transaction_fk1`
        FOREIGN KEY (`beneficiary_user_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
