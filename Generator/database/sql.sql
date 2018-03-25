DROP DATABASE IF EXISTS rest;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema rest
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rest` DEFAULT CHARACTER SET utf8 ;
USE `rest` ;

-- -----------------------------------------------------
-- Table `rest`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rest`.`author` (
  `author_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `country` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`author_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rest`.`resource`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rest`.`resource` (
  `resource_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `url` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`resource_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rest`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rest`.`article` (
  `article_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `body` TEXT NOT NULL,
  `date` DATE NOT NULL,
  `fk_author_id` INT UNSIGNED NOT NULL,
  `fk_resource_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`article_id`),
  INDEX `fk_article_1_idx` (`fk_author_id` ASC),
  INDEX `fk_article_2_idx` (`fk_resource_id` ASC),
  CONSTRAINT `fk_article_1`
    FOREIGN KEY (`fk_author_id`)
    REFERENCES `rest`.`author` (`author_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_2`
    FOREIGN KEY (`fk_resource_id`)
    REFERENCES `rest`.`resource` (`resource_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO resource(name,url) VALUES("google","http://google.com"),("stackoverflow","http://stackoverflow.com");
INSERT INTO resource(name,url) VALUES("bsuir","http://bsuir.by"),("github","http://github.com");
INSERT INTO resource(name,url) VALUES("facebook","http://facebook.com"),("twitter","http://twitter.com");
