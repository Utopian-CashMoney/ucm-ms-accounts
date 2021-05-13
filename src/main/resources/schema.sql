-- MySQL Script generated by MySQL Workbench
-- Sun May  9 22:49:31 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

--SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
--SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
--SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema utopia_cashmoney
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema utopia_cashmoney
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `utopia_cashmoney`;

-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(31) NOT NULL,
  `email` VARCHAR(127) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(31) NULL,
  `first_name` VARCHAR(63) NOT NULL,
  `last_name` VARCHAR(63) NOT NULL,
  PRIMARY KEY (`id`));

CREATE UNIQUE INDEX `email_UNIQUE` ON `utopia_cashmoney`.`users`(`email` ASC);
CREATE UNIQUE INDEX `username_UNIQUE` ON `utopia_cashmoney`.`users`(`username` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`account_debit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`account_debit` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

CREATE UNIQUE INDEX `name_UNIQUE` ON `utopia_cashmoney`.`account_debit` (`name` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`account_credit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`account_credit` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `limit` DECIMAL(20,2) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`users_has_account_debit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`users_has_account_debit` (
  `account_number` VARCHAR(31) NOT NULL,
  `users_id` INT NOT NULL,
  `account_debit_id` INT NOT NULL,
  `funds` DECIMAL(20,2) NOT NULL,
  PRIMARY KEY (`account_number`),
  CONSTRAINT `fk_users_has_account_debit_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `utopia_cashmoney`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_account_debit_account_debit1`
    FOREIGN KEY (`account_debit_id`)
    REFERENCES `utopia_cashmoney`.`account_debit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_users_has_account_debit_account_debit1_idx` ON `utopia_cashmoney`.`users_has_account_debit` (`account_debit_id` ASC);
CREATE INDEX `fk_users_has_account_debit_users_idx` ON `utopia_cashmoney`.`users_has_account_debit` (`users_id` ASC);
CREATE UNIQUE INDEX `account_number_UNIQUE` ON `utopia_cashmoney`.`users_has_account_debit` (`account_number` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`users_has_account_credit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`users_has_account_credit` (
  `account_number` VARCHAR(31) NOT NULL,
  `users_id` INT NOT NULL,
  `account_credit_id` INT NOT NULL,
  `balance` DECIMAL(20,2) NOT NULL,
  PRIMARY KEY (`account_number`),
  CONSTRAINT `fk_users_has_account_credit_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `utopia_cashmoney`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_account_credit_account_credit1`
    FOREIGN KEY (`account_credit_id`)
    REFERENCES `utopia_cashmoney`.`account_credit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_users_has_account_credit_account_credit1_idx` ON `utopia_cashmoney`.`users_has_account_credit` (`account_credit_id` ASC);
CREATE INDEX `fk_users_has_account_credit_users1_idx` ON `utopia_cashmoney`.`users_has_account_credit` (`users_id` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`debit_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`debit_transaction` (
  `id` INT NOT NULL,
  `account_number` VARCHAR(31) NOT NULL,
  `amount` DECIMAL(20,2) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `processed` TINYINT NOT NULL,
  `cancelled` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_debit_transaction_users_has_account_debit1`
    FOREIGN KEY (`account_number`)
    REFERENCES `utopia_cashmoney`.`users_has_account_debit` (`account_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_debit_transaction_users_has_account_debit1_idx` ON `utopia_cashmoney`.`debit_transaction` (`account_number` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`credit_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`credit_transaction` (
  `id` INT NOT NULL,
  `account_number` VARCHAR(31) NOT NULL,
  `amount` DECIMAL(20,2) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `processed` TINYINT NOT NULL,
  `cancelled` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_credit_transaction_users_has_account_credit1`
    FOREIGN KEY (`account_number`)
    REFERENCES `utopia_cashmoney`.`users_has_account_credit` (`account_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_credit_transaction_users_has_account_credit1_idx` ON `utopia_cashmoney`.`credit_transaction` (`account_number` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`debit_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`debit_card` (
  `card_number` VARCHAR(31) NOT NULL,
  `expiration` DATE NOT NULL,
  `account_number` VARCHAR(31) NOT NULL,
  `cvv` CHAR(3) NOT NULL,
  PRIMARY KEY (`card_number`, `expiration`),
  CONSTRAINT `fk_debit_card_users_has_account_debit1`
    FOREIGN KEY (`account_number`)
    REFERENCES `utopia_cashmoney`.`users_has_account_debit` (`account_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_debit_card_users_has_account_debit1_idx` ON `utopia_cashmoney`.`debit_card` (`account_number` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`credit_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`credit_card` (
  `card_number` VARCHAR(31) NOT NULL,
  `expiration` DATE NOT NULL,
  `account_number` VARCHAR(31) NOT NULL,
  `cvv` CHAR(3) NOT NULL,
  PRIMARY KEY (`card_number`, `expiration`),
  CONSTRAINT `fk_credit_card_users_has_account_credit1`
    FOREIGN KEY (`account_number`)
    REFERENCES `utopia_cashmoney`.`users_has_account_credit` (`account_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_credit_card_users_has_account_credit1_idx` ON `utopia_cashmoney`.`credit_card` (`account_number` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`loan` (
  `id` INT NOT NULL,
  `max_amount` DECIMAL(20,2) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `interest_rate` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`user_loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`user_loan` (
  `loan_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  `balance` DECIMAL(20,2) NOT NULL,
  `start_date` DATE NOT NULL,
  PRIMARY KEY (`loan_id`, `users_id`),
  CONSTRAINT `fk_loan_has_users_loan1`
    FOREIGN KEY (`loan_id`)
    REFERENCES `utopia_cashmoney`.`loan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_loan_has_users_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `utopia_cashmoney`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_loan_has_users_users1_idx` ON `utopia_cashmoney`.`user_loan` (`users_id` ASC);
CREATE INDEX `fk_loan_has_users_loan1_idx` ON `utopia_cashmoney`.`user_loan` (`loan_id` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`branch` (
  `location_number` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `zip` VARCHAR(16) NOT NULL,
  `street_address` VARCHAR(255) NOT NULL,
  `city` VARCHAR(127) NOT NULL,
  `opening_time` TIME NOT NULL,
  `closing_time` TIME NOT NULL,
  PRIMARY KEY (`location_number`));


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`banker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`banker` (
  `id` INT NOT NULL,
  `branch_location_number` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_banker_branch1`
    FOREIGN KEY (`branch_location_number`)
    REFERENCES `utopia_cashmoney`.`branch` (`location_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_banker_branch1_idx` ON `utopia_cashmoney`.`banker` (`branch_location_number` ASC);


-- -----------------------------------------------------
-- Table `utopia_cashmoney`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `utopia_cashmoney`.`appointment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `branch_location_number` INT NOT NULL,
  `users_id` INT NOT NULL,
  `banker_id` INT NOT NULL,
  `scheduled_time` DATETIME NOT NULL,
  `service` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_branch_has_users_branch1`
    FOREIGN KEY (`branch_location_number`)
    REFERENCES `utopia_cashmoney`.`branch` (`location_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_branch_has_users_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `utopia_cashmoney`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_banker1`
    FOREIGN KEY (`banker_id`)
    REFERENCES `utopia_cashmoney`.`banker` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE INDEX `fk_branch_has_users_users1_idx` ON `utopia_cashmoney`.`appointment` (`users_id` ASC);
CREATE INDEX `fk_branch_has_users_branch1_idx` ON `utopia_cashmoney`.`appointment` (`branch_location_number` ASC);
CREATE INDEX `fk_appointment_banker1_idx` ON `utopia_cashmoney`.`appointment` (`banker_id` ASC);