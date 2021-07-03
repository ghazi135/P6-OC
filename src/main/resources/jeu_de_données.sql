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

