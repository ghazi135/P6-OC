DROP DATABASE IF EXISTS `paymybuddy`;

CREATE DATABASE `paymybuddy`;
USE `paymybuddy`;
CREATE TABLE `user`
(
    `id`         INT AUTO_INCREMENT NOT NULL,
    `first_name` VARCHAR(30)        NOT NULL,
    `last_name`  VARCHAR(40)        NOT NULL,
    `email`      VARCHAR(40)        NOT NULL,
    `balance`    DECIMAL(10, 2)     NOT NULL,
    `password`   VARCHAR(1000)      NOT NULL,
    `enabled`    BOOLEAN DEFAULT 1,
    `role`       VARCHAR(30),
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



INSERT INTO `User`(`first_name`, `last_name`, `email`, `balance`, `password`, `enabled`, `role`)
VALUES ('alex', 'dupont', 'alex@gmail.com', 100, '11111', 1, 'User');

INSERT INTO `User`(`first_name`, `last_name`, `email`, `balance`, `password`, `enabled`, `role`)
VALUES ('ghazi', 'bouzazi', 'rayen@gmail.com', 100, '11111', 1, 'User');

INSERT INTO `User`(`first_name`, `last_name`, `email`, `balance`, `password`, `enabled`, `role`)
VALUES ('alice', 'lemaire', 'alice@gmail.com', 100, '11111', 1, 'User');

INSERT INTO `bank_account`(`iban`, `account_number`, `user_id`)
VALUES ('FR785214', 123456, 1);

INSERT INTO `bank_account`(`iban`, `account_number`, `user_id`)
VALUES ('FR489652', 4568962, 2);

INSERT INTO `bank_account`(`iban`, `account_number`, `user_id`)
VALUES ('FR46789', 147896325, 3);

INSERT INTO `transaction`(`description`, `amount`, `beneficiary_user_id`, `payer_user_id`)
VALUES ('blabla', 10, 1, 2);

INSERT INTO `transaction`(`description`, `amount`, `beneficiary_user_id`, `payer_user_id`)
VALUES ('blabla', 10, 1, 3);

INSERT INTO `transaction`(`description`, `amount`, `beneficiary_user_id`, `payer_user_id`)
VALUES ('blabla', 10, 2, 1);

INSERT INTO `transaction`(`description`, `amount`, `beneficiary_user_id`, `payer_user_id`)
VALUES ('blabla', 10, 2, 3);

INSERT INTO `transaction`(`description`, `amount`, `beneficiary_user_id`, `payer_user_id`)
VALUES ('blabla', 10, 3, 1);

INSERT INTO `transaction`(`description`, `amount`, `beneficiary_user_id`, `payer_user_id`)
VALUES ('blabla', 10, 3, 2);

INSERT INTO `user_friends`(`user_id`, `user_friend_id`)
VALUE (1,2);

INSERT INTO `user_friends`(`user_id`, `user_friend_id`)
    VALUE (2,1);

INSERT INTO `user_friends`(`user_id`, `user_friend_id`)
    VALUE (1,3);

INSERT INTO `user_friends`(`user_id`, `user_friend_id`)
    VALUE (3,1);

INSERT INTO `user_friends`(`user_id`, `user_friend_id`)
    VALUE (2,3);

INSERT INTO `user_friends`(`user_id`, `user_friend_id`)
    VALUE (3,2);


