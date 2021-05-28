-- MySQL Script generated by MySQL Workbench
-- Sat May 15 15:22:40 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(31)  NOT NULL,
    `email`      VARCHAR(127) NOT NULL,
    `password`   VARCHAR(255) NOT NULL,
    `phone`      VARCHAR(31)  NULL,
    `first_name` VARCHAR(63)  NOT NULL,
    `last_name`  VARCHAR(63)  NOT NULL,
    `is_active`  BOOLEAN      NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE UNIQUE INDEX `email_UNIQUE` ON `users` (`email` ASC);

CREATE UNIQUE INDEX `username_UNIQUE` ON `users` (`username` ASC);

-- -----------------------------------------------------
-- Table `confirm_token`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `confirm_token`
(
    `token_id`              INT             NOT NULL AUTO_INCREMENT,
    `users_id`              INT             NOT NULL,
    `confirmation_token`    VARCHAR(255)    NOT NULL,
    `created_date`          DATETIME        NOT NULL,
    CONSTRAINT `fk_confirm_token_users1`
        FOREIGN KEY (`users_id`)
            REFERENCES `users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    PRIMARY KEY (`token_id`)
);

CREATE UNIQUE INDEX `fk_confirm_token_users1_idx` ON `confirm_token` (`users_id` ASC);


-- -----------------------------------------------------
-- Table `loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loan`
(
    `id`            INT            NOT NULL,
    `max_amount`    DECIMAL(20, 2) NOT NULL,
    `name`          VARCHAR(255)   NOT NULL,
    `interest_rate` DECIMAL(5, 2)  NOT NULL,
    PRIMARY KEY (`id`)
);


-- -----------------------------------------------------
-- Table `user_loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_loan`
(
    `loan_id`    INT            NOT NULL,
    `users_id`   INT            NOT NULL,
    `balance`    DECIMAL(20, 2) NOT NULL,
    `start_date` DATE           NOT NULL,
    PRIMARY KEY (`loan_id`, `users_id`),
    CONSTRAINT `fk_loan_has_users_loan1`
        FOREIGN KEY (`loan_id`)
            REFERENCES `loan` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_loan_has_users_users1`
        FOREIGN KEY (`users_id`)
            REFERENCES `users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX `fk_loan_has_users_users1_idx` ON `user_loan` (`users_id` ASC);

CREATE INDEX `fk_loan_has_users_loan1_idx` ON `user_loan` (`loan_id` ASC);


-- -----------------------------------------------------
-- Table `branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `branch`
(
    `location_number` INT          NOT NULL,
    `name`            VARCHAR(255) NOT NULL,
    `zip`             VARCHAR(16)  NOT NULL,
    `street_address`  VARCHAR(255) NOT NULL,
    `city`            VARCHAR(127) NOT NULL,
    `opening_time`    TIME         NOT NULL,
    `closing_time`    TIME         NOT NULL,
    PRIMARY KEY (`location_number`)
);


-- -----------------------------------------------------
-- Table `banker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `banker`
(
    `id`                     INT          NOT NULL,
    `branch_location_number` INT          NOT NULL,
    `name`                   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_banker_branch1`
        FOREIGN KEY (`branch_location_number`)
            REFERENCES `branch` (`location_number`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX `fk_banker_branch1_idx` ON `banker` (`branch_location_number` ASC);


-- -----------------------------------------------------
-- Table `appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `appointment`
(
    `id`                     INT          NOT NULL AUTO_INCREMENT,
    `branch_location_number` INT          NOT NULL,
    `users_id`               INT          NOT NULL,
    `banker_id`              INT          NOT NULL,
    `scheduled_time`         DATETIME     NOT NULL,
    `service`                VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_branch_has_users_branch1`
        FOREIGN KEY (`branch_location_number`)
            REFERENCES `branch` (`location_number`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_branch_has_users_users1`
        FOREIGN KEY (`users_id`)
            REFERENCES `users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_appointment_banker1`
        FOREIGN KEY (`banker_id`)
            REFERENCES `banker` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX `fk_branch_has_users_users1_idx` ON `appointment` (`users_id` ASC);

CREATE INDEX `fk_branch_has_users_branch1_idx` ON `appointment` (`branch_location_number` ASC);

CREATE INDEX `fk_appointment_banker1_idx` ON `appointment` (`banker_id` ASC);


-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account`
(
    `id`           INT                      NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45)              NOT NULL,
    `type`         VARCHAR(255)             NOT NULL,
    `allow_credit` BOOLEAN                  NOT NULL DEFAULT 0,
    `credit_limit` DECIMAL(20, 2)           NOT NULL DEFAULT 0,
    `allow_cards`  BOOLEAN                  NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);


-- -----------------------------------------------------
-- Table `user_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_account`
(
    `account_number` VARCHAR(31)    NOT NULL,
    `users_id`       INT            NOT NULL,
    `account_id`     INT            NOT NULL,
    `balance`        DECIMAL(20, 2) NOT NULL,
    `active`         BOOLEAN        NOT NULL,
    PRIMARY KEY (`account_number`),
    CONSTRAINT `fk_users_has_account_users1`
        FOREIGN KEY (`users_id`)
            REFERENCES `users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_users_has_account_account1`
        FOREIGN KEY (`account_id`)
            REFERENCES `account` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX `fk_users_has_account_account1_idx` ON `user_account` (`account_id` ASC);

CREATE INDEX `fk_users_has_account_users1_idx` ON `user_account` (`users_id` ASC);

-- -----------------------------------------------------
-- Table `user_account_confirmation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_account_confirmation` (
    `user_account_id`   VARCHAR(31)     NOT NULL,
    `code`              VARCHAR(255)    NOT NULL,
    `expires`           DATETIME        NOT NULL,
    PRIMARY KEY (`user_account_id`),
    CONSTRAINT `fk_user_account_confirmation_has_user_account1`
        FOREIGN KEY (`user_account_id`)
            REFERENCES `user_account` (`account_number`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
);

CREATE INDEX `fk_user_account_confirmation_has_user_account1_idx` ON `user_account_confirmation` (`user_account_id` ASC);
CREATE INDEX `code_UNIQUE` ON `user_account_confirmation` (`code`);


-- -----------------------------------------------------
-- Table `transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transaction`
(
    `id`             INT            NOT NULL AUTO_INCREMENT,
    `account_number` VARCHAR(31)    NOT NULL,
    `amount`         DECIMAL(20, 2) NOT NULL,
    `timestamp`      TIMESTAMP      NOT NULL,
    `name`           VARCHAR(255)   NOT NULL,
    `is_processed`   BOOLEAN        NOT NULL,
    `is_cancelled`   BOOLEAN        NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_transaction_user_account1`
        FOREIGN KEY (`account_number`)
            REFERENCES `user_account` (`account_number`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX `fk_transaction_user_account1_idx` ON `transaction` (`account_number` ASC);


-- -----------------------------------------------------
-- Table `card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `card`
(
    `id`             INT         NOT NULL AUTO_INCREMENT,
    `account_number` VARCHAR(31) NOT NULL,
    `card_number`    VARCHAR(31) NOT NULL,
    `exp`            DATE        NOT NULL,
    `cvv`            VARCHAR(3)  NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_card_user_account1`
        FOREIGN KEY (`account_number`)
            REFERENCES `user_account` (`account_number`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX `fk_card_user_account1_idx` ON `card` (`account_number` ASC);
