--Accounts
INSERT INTO `account` VALUES ( 1, 'Primary Checking', 'DEBIT', true, 100, 1 );
INSERT INTO `account` VALUES ( 2, 'Primary Savings', 'DEBIT', false, 0, 0 );
INSERT INTO `account` VALUES ( 3, 'Primary Credit', 'CREDIT', true, 500, 1 );

INSERT INTO `role` VALUES ( 1, 'ROLE_USER' );
INSERT INTO `role` VALUES ( 2, 'ROLE_ADMIN' );

--Users
INSERT INTO `users` VALUES ( 1, 'username', 'email@website.com', 'unhashed_password', null, 'John', 'Doe', 1, 1);

--UserAccounts
INSERT INTO `user_account` VALUES ('CHKACCTNMBR', 1, 1, 125.32, true);
INSERT INTO `user_account` VALUES ('SVNGACCTNMBR', 1, 2, 753.25, false);
INSERT INTO `user_account` VALUES ('CRDTACCTNMBR', 1, 3, -25.36, false);

--UserAccountConfirmation
INSERT INTO `user_account_confirmation` VALUES ('SVNGACCTNMBR', 'code', CURRENT_DATE() + 1);

--Cards
INSERT INTO `card` VALUES ( 1, 'CHKACCTNMBR', 'CHKCARDNMBR',  CURDATE(), '333' );
INSERT INTO `card` VALUES ( 2, 'CRDTACCTNMBR', 'CRDTCARDNMBR', CURDATE(), '666' );

--Transactions
INSERT INTO `transaction` VALUES (1, 'CRDTACCTNMBR', 99, CURRENT_TIMESTAMP() + 1, 'NOPE', 'CANCELLED', 'NOPER');
INSERT INTO `transaction` VALUES (2, 'CHKACCTNMBR', 100, CURRENT_TIMESTAMP() - 1, 'SOMEONE GIV U $', 'COMPLETE', 'GIVER');
INSERT INTO `transaction` VALUES (3, 'CHKACCTNMBR', -100, CURRENT_TIMESTAMP(), 'SOMEONE TAK UR $', 'PENDING', 'TAKER');
