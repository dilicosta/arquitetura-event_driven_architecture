CREATE DATABASE IF NOT EXISTS wallet;
USE wallet;
CREATE TABLE IF NOT EXISTS `wallet`.`clients` (
  `id` VARCHAR(255) PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `wallet`.`accounts` (
  `id` VARCHAR(255) PRIMARY KEY,
  `client_id` VARCHAR(255) NOT NULL ,
  `balance` DOUBLE(10, 2),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `wallet`.`transactions` (
  `id` VARCHAR(255) PRIMARY KEY,
  `account_id_from` VARCHAR(255) NOT NULL ,
  `account_id_to` VARCHAR(255) NOT NULL ,
  `amount` DOUBLE(10, 2),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO `wallet`.`clients` (id, name,email) VALUES ('f8df753c-3b58-43aa-8016-12aaa4f1ea3e', 'diego', 'diego@diego.com');
INSERT INTO `wallet`.`clients` (id, name,email) VALUES ('0216ea38-524f-4e85-8743-d484a8f7538e', 'teste', 'teste@teste.com');
INSERT INTO `wallet`.`accounts`(id, client_id,balance) VALUES ('87495b95-1c7f-4038-ae55-ab36ed6a9411','f8df753c-3b58-43aa-8016-12aaa4f1ea3e', 100.00);
INSERT INTO `wallet`.`accounts`(id, client_id,balance) VALUES ('9e3c6bb1-bf75-11e9-9ea7-2a2ae2dbcce4','0216ea38-524f-4e85-8743-d484a8f7538e', 25.00);