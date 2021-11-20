-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hackaton_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hackaton_db` ;

-- -----------------------------------------------------
-- Schema hackaton_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hackaton_db` DEFAULT CHARACTER SET utf8 ;
USE `hackaton_db` ;

-- -----------------------------------------------------
-- Table `hackaton_db`.`rols`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`rols` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`rols` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `name_rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`users` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`users` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `code_role_user` INT NOT NULL DEFAULT 2,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `disabled` INT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `users_rols_idx` (`code_role_user` ASC) VISIBLE,
  CONSTRAINT `users_rols`
    FOREIGN KEY (`code_role_user`)
    REFERENCES `hackaton_db`.`rols` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`categories` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`categories` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`products` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`products` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `code_category_product` INT NOT NULL,
  `code_user_product` INT NOT NULL,
  `title` TEXT NOT NULL,
  `description` TEXT NOT NULL,
  `price` DOUBLE NOT NULL DEFAULT 0,
  `available` INT NOT NULL,
  `not_available` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC, `code_category_product` ASC) VISIBLE,
  INDEX `category_idx` (`code_category_product` ASC) VISIBLE,
  INDEX `products_user_idx` (`code_user_product` ASC) VISIBLE,
  CONSTRAINT `products_categories`
    FOREIGN KEY (`code_category_product`)
    REFERENCES `hackaton_db`.`categories` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `products_user`
    FOREIGN KEY (`code_user_product`)
    REFERENCES `hackaton_db`.`users` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`contract`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`contract` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`contract` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `code_user_lessee_contract` INT NOT NULL,
  `code_product_contract` INT NOT NULL,
  `date_init` DATE NOT NULL,
  `date_final` DATE NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `contract_user_lessee_idx` (`code_user_lessee_contract` ASC) VISIBLE,
  INDEX `contract_product_idx` (`code_product_contract` ASC) VISIBLE,
  CONSTRAINT `contract_user_lessee`
    FOREIGN KEY (`code_user_lessee_contract`)
    REFERENCES `hackaton_db`.`users` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `contract_product`
    FOREIGN KEY (`code_product_contract`)
    REFERENCES `hackaton_db`.`products` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`images_products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`images_products` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`images_products` (
  `code` INT NOT NULL,
  `code_product_image` INT NOT NULL,
  `url` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `product_image_idx` (`code_product_image` ASC) VISIBLE,
  CONSTRAINT `product_image`
    FOREIGN KEY (`code_product_image`)
    REFERENCES `hackaton_db`.`products` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`countries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`countries` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`countries` (
  `code` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`departments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`departments` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`departments` (
  `code` INT NOT NULL,
  `code_country_department` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `departments_countries_idx` (`code_country_department` ASC) VISIBLE,
  CONSTRAINT `departments_countries`
    FOREIGN KEY (`code_country_department`)
    REFERENCES `hackaton_db`.`countries` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`municipalities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`municipalities` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`municipalities` (
  `code` INT NOT NULL,
  `code_department_municipality` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `municipalities_departments_idx` (`code_department_municipality` ASC) VISIBLE,
  CONSTRAINT `municipalities_departments`
    FOREIGN KEY (`code_department_municipality`)
    REFERENCES `hackaton_db`.`departments` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hackaton_db`.`info_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hackaton_db`.`info_user` ;

CREATE TABLE IF NOT EXISTS `hackaton_db`.`info_user` (
  `code` INT NOT NULL,
  `code_municipality` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `age` INT(2) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `zip_code` VARCHAR(45) NOT NULL,
  `message` VARCHAR(255) NULL,
  PRIMARY KEY (`code`),
  INDEX `info_user_municipalities_idx` (`code_municipality` ASC) VISIBLE,
  CONSTRAINT `info_user_users`
    FOREIGN KEY (`code`)
    REFERENCES `hackaton_db`.`users` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `info_user_municipalities`
    FOREIGN KEY (`code_municipality`)
    REFERENCES `hackaton_db`.`municipalities` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
