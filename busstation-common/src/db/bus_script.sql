-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema station
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `station` ;

-- -----------------------------------------------------
-- Schema station
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `station` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `station` ;

-- -----------------------------------------------------
-- Table `station`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `station`.`users` ;

CREATE TABLE IF NOT EXISTS `station`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `station`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `station`.`roles` ;

CREATE TABLE IF NOT EXISTS `station`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_roles_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_roles_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `station`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `station`.`bus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `station`.`bus` ;

CREATE TABLE IF NOT EXISTS `station`.`bus` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reg_number` VARCHAR(45) NOT NULL,
  `inspection_date` TIMESTAMP NOT NULL,
  `seats_count` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `station`.`driver`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `station`.`driver` ;

CREATE TABLE IF NOT EXISTS `station`.`driver` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `experience` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `station`.`places`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `station`.`places` ;

CREATE TABLE IF NOT EXISTS `station`.`places` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `station`.`route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `station`.`route` ;

CREATE TABLE IF NOT EXISTS `station`.`route` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `destination_date` TIMESTAMP NOT NULL,
  `departure_date` TIMESTAMP NOT NULL,
  `cost` INT NOT NULL,
  `destination_id` INT NOT NULL,
  `departure_id` INT NOT NULL,
  `bus_id` INT NOT NULL,
  `driver_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_route_places1_idx` (`destination_id` ASC),
  INDEX `fk_route_places2_idx` (`departure_id` ASC),
  INDEX `fk_route_bus1_idx` (`bus_id` ASC),
  INDEX `fk_route_driver1_idx` (`driver_id` ASC),
  CONSTRAINT `fk_route_places1`
    FOREIGN KEY (`destination_id`)
    REFERENCES `station`.`places` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_places2`
    FOREIGN KEY (`departure_id`)
    REFERENCES `station`.`places` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_bus1`
    FOREIGN KEY (`bus_id`)
    REFERENCES `station`.`bus` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_driver1`
    FOREIGN KEY (`driver_id`)
    REFERENCES `station`.`driver` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `station`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `station`.`ticket` ;

CREATE TABLE IF NOT EXISTS `station`.`ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `users_id` INT NOT NULL,
  `route_id` INT NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 0,
  INDEX `fk_users_has_route_route1_idx` (`route_id` ASC),
  INDEX `fk_users_has_route_users1_idx` (`users_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_users_has_route_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `station`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_route_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `station`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `station`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `station`;
INSERT INTO `station`.`users` (`id`, `login`, `password`, `email`) VALUES (1, 'admin', 'admin', 'admin@localhost');

COMMIT;


-- -----------------------------------------------------
-- Data for table `station`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `station`;
INSERT INTO `station`.`roles` (`id`, `name`, `users_id`) VALUES (1, 'ROLE_ADMIN', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `station`.`bus`
-- -----------------------------------------------------
START TRANSACTION;
USE `station`;
INSERT INTO `station`.`bus` (`id`, `reg_number`, `inspection_date`, `seats_count`) VALUES (1, '1234 HB-7', '2015-05-05', 20);
INSERT INTO `station`.`bus` (`id`, `reg_number`, `inspection_date`, `seats_count`) VALUES (2, '0908 BI-5', '2016-02-08', 25);
INSERT INTO `station`.`bus` (`id`, `reg_number`, `inspection_date`, `seats_count`) VALUES (3, 'TAX 3449-7', '2014-09-12', 15);
INSERT INTO `station`.`bus` (`id`, `reg_number`, `inspection_date`, `seats_count`) VALUES (4, '3419 IH-4', '2015-05-19', 25);
INSERT INTO `station`.`bus` (`id`, `reg_number`, `inspection_date`, `seats_count`) VALUES (5, 'TAX 3971-5', '2016-05-09', 50);
INSERT INTO `station`.`bus` (`id`, `reg_number`, `inspection_date`, `seats_count`) VALUES (6, 'TAX 1004-2', '2015-12-19', 45);

COMMIT;


-- -----------------------------------------------------
-- Data for table `station`.`driver`
-- -----------------------------------------------------
START TRANSACTION;
USE `station`;
INSERT INTO `station`.`driver` (`id`, `name`, `experience`) VALUES (1, 'Иванов Антон Петрович', 10);
INSERT INTO `station`.`driver` (`id`, `name`, `experience`) VALUES (2, 'Сурков Кирилл Андреевич', 5);
INSERT INTO `station`.`driver` (`id`, `name`, `experience`) VALUES (3, 'Антонов Василий Иванович', 15);
INSERT INTO `station`.`driver` (`id`, `name`, `experience`) VALUES (4, 'Кулешов Пётр Антонович', 4);
INSERT INTO `station`.`driver` (`id`, `name`, `experience`) VALUES (5, 'Игнатенко Алексей Дмитриевич', 7);
INSERT INTO `station`.`driver` (`id`, `name`, `experience`) VALUES (6, 'Власенко Дмитрий Петрович', 12);

COMMIT;


-- -----------------------------------------------------
-- Data for table `station`.`places`
-- -----------------------------------------------------
START TRANSACTION;
USE `station`;
INSERT INTO `station`.`places` (`id`, `name`) VALUES (1, 'Брест');
INSERT INTO `station`.`places` (`id`, `name`) VALUES (2, 'Витебск');
INSERT INTO `station`.`places` (`id`, `name`) VALUES (3, 'Гомель');
INSERT INTO `station`.`places` (`id`, `name`) VALUES (4, 'Гродно');
INSERT INTO `station`.`places` (`id`, `name`) VALUES (5, 'Минск');
INSERT INTO `station`.`places` (`id`, `name`) VALUES (6, 'Могилёв');

COMMIT;

