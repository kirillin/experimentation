SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `bts` ;
CREATE SCHEMA IF NOT EXISTS `bts` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bts` ;

-- -----------------------------------------------------
-- Table `bts`.`department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bts`.`department` ;

CREATE  TABLE IF NOT EXISTS `bts`.`department` (
  `department_id` INT(3) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `department_name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`department_id`) ,
  UNIQUE INDEX `department_id_UNIQUE` (`department_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bts`.`errors_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bts`.`errors_status` ;

CREATE  TABLE IF NOT EXISTS `bts`.`errors_status` (
  `errors_status_id` INT NOT NULL AUTO_INCREMENT ,
  `status_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`errors_status_id`) ,
  UNIQUE INDEX `errors_status_id_UNIQUE` (`errors_status_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bts`.`error_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bts`.`error_level` ;

CREATE  TABLE IF NOT EXISTS `bts`.`error_level` (
  `error_level_id` INT NOT NULL AUTO_INCREMENT ,
  `level_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`error_level_id`) ,
  UNIQUE INDEX `error_level_id_UNIQUE` (`error_level_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bts`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bts`.`project` ;

CREATE  TABLE IF NOT EXISTS `bts`.`project` (
  `project_id` INT NOT NULL AUTO_INCREMENT ,
  `project_name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`project_id`) ,
  UNIQUE INDEX `project_id_UNIQUE` (`project_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bts`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bts`.`employee` ;

CREATE  TABLE IF NOT EXISTS `bts`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT ,
  `department_id` INT(3) ZEROFILL NOT NULL ,
  `first_name` VARCHAR(45) NOT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  `middle_name` VARCHAR(45) NULL ,
  `isDeleted` TINYINT(1) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`employee_id`) ,
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC) ,
  INDEX `fk_employee_department1_idx` (`department_id` ASC) ,
  CONSTRAINT `fk_employee_department1`
    FOREIGN KEY (`department_id` )
    REFERENCES `bts`.`department` (`department_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bts`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bts`.`status` ;

CREATE  TABLE IF NOT EXISTS `bts`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT ,
  `error_id` INT NOT NULL ,
  `errors_status_id` INT NOT NULL ,
  `employee_id` INT NOT NULL ,
  `date_setting` TIMESTAMP NOT NULL ,
  PRIMARY KEY (`status_id`) ,
  UNIQUE INDEX `status_id_UNIQUE` (`status_id` ASC) ,
  INDEX `fk_status_errors_status1_idx` (`errors_status_id` ASC) ,
  INDEX `fk_status_employee1_idx` (`employee_id` ASC) ,
  INDEX `fk_status_error1_idx` (`error_id` ASC) ,
  CONSTRAINT `fk_status_errors_status1`
    FOREIGN KEY (`errors_status_id` )
    REFERENCES `bts`.`errors_status` (`errors_status_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_employee1`
    FOREIGN KEY (`employee_id` )
    REFERENCES `bts`.`employee` (`employee_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_error1`
    FOREIGN KEY (`error_id` )
    REFERENCES `bts`.`error` (`error_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bts`.`error`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bts`.`error` ;

CREATE  TABLE IF NOT EXISTS `bts`.`error` (
  `error_id` INT NOT NULL AUTO_INCREMENT ,
  `status_id` INT NULL DEFAULT NULL ,
  `error_level_id` INT NOT NULL ,
  `project_id` INT NOT NULL ,
  `employee_id` INT NOT NULL ,
  `register_date` TIMESTAMP NULL ,
  `description` VARCHAR(1000) NULL DEFAULT NULL ,
  PRIMARY KEY (`error_id`) ,
  UNIQUE INDEX `error_id_UNIQUE` (`error_id` ASC) ,
  INDEX `fk_error_error_level1_idx` (`error_level_id` ASC) ,
  INDEX `fk_error_project1_idx` (`project_id` ASC) ,
  INDEX `fk_error_employee1_idx` (`employee_id` ASC) ,
  INDEX `fk_error_status1_idx` (`status_id` ASC) ,
  CONSTRAINT `fk_error_error_level1`
    FOREIGN KEY (`error_level_id` )
    REFERENCES `bts`.`error_level` (`error_level_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_error_project1`
    FOREIGN KEY (`project_id` )
    REFERENCES `bts`.`project` (`project_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_error_employee1`
    FOREIGN KEY (`employee_id` )
    REFERENCES `bts`.`employee` (`employee_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_error_status1`
    FOREIGN KEY (`status_id` )
    REFERENCES `bts`.`status` (`status_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bts`.`employee_has_project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bts`.`employee_has_project` ;

CREATE  TABLE IF NOT EXISTS `bts`.`employee_has_project` (
  `project_id` INT NOT NULL ,
  `employee_id` INT NOT NULL ,
  PRIMARY KEY (`project_id`, `employee_id`) ,
  INDEX `fk_employee_has_project_project1_idx` (`project_id` ASC) ,
  INDEX `fk_employee_has_project_employee1_idx` (`employee_id` ASC) ,
  CONSTRAINT `fk_employee_has_project_employee1`
    FOREIGN KEY (`employee_id` )
    REFERENCES `bts`.`employee` (`employee_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_has_project_project1`
    FOREIGN KEY (`project_id` )
    REFERENCES `bts`.`project` (`project_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `bts` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `bts`.`department`
-- -----------------------------------------------------
START TRANSACTION;
USE `bts`;
INSERT INTO `bts`.`department` (`department_id`, `department_name`) VALUES (001, 'Отдел сбора и анализа требований');
INSERT INTO `bts`.`department` (`department_id`, `department_name`) VALUES (002, 'Отдел проектирования программных продуктов');
INSERT INTO `bts`.`department` (`department_id`, `department_name`) VALUES (003, 'Отдел разработки программных продуктов');
INSERT INTO `bts`.`department` (`department_id`, `department_name`) VALUES (004, 'Отдел обеспечения качества программных продуктов ');
INSERT INTO `bts`.`department` (`department_id`, `department_name`) VALUES (005, 'Отдел внедрения программных продуктов');

COMMIT;

-- -----------------------------------------------------
-- Data for table `bts`.`errors_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `bts`;
INSERT INTO `bts`.`errors_status` (`errors_status_id`, `status_name`) VALUES (1, 'Зарегистрирована');
INSERT INTO `bts`.`errors_status` (`errors_status_id`, `status_name`) VALUES (2, 'Решена');
INSERT INTO `bts`.`errors_status` (`errors_status_id`, `status_name`) VALUES (3, 'Отклонена');

COMMIT;

-- -----------------------------------------------------
-- Data for table `bts`.`error_level`
-- -----------------------------------------------------
START TRANSACTION;
USE `bts`;
INSERT INTO `bts`.`error_level` (`error_level_id`, `level_name`) VALUES (1, 'Тривиальная');
INSERT INTO `bts`.`error_level` (`error_level_id`, `level_name`) VALUES (2, 'Минорная');
INSERT INTO `bts`.`error_level` (`error_level_id`, `level_name`) VALUES (3, 'Мажорная');
INSERT INTO `bts`.`error_level` (`error_level_id`, `level_name`) VALUES (4, 'Критическая');
INSERT INTO `bts`.`error_level` (`error_level_id`, `level_name`) VALUES (5, 'Блокирующая');

COMMIT;

-- -----------------------------------------------------
-- Data for table `bts`.`project`
-- -----------------------------------------------------
START TRANSACTION;
USE `bts`;
INSERT INTO `bts`.`project` (`project_id`, `project_name`) VALUES (1, 'АИС МЧС');
INSERT INTO `bts`.`project` (`project_id`, `project_name`) VALUES (2, 'АИС МВД РК');
INSERT INTO `bts`.`project` (`project_id`, `project_name`) VALUES (3, 'АИС Минздрав');

COMMIT;

-- -----------------------------------------------------
-- Data for table `bts`.`employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `bts`;
INSERT INTO `bts`.`employee` (`employee_id`, `department_id`, `first_name`, `last_name`, `middle_name`, `isDeleted`) VALUES (1, 001, 'Пупкин', 'Вася', 'Алибабаевич', 0);

COMMIT;
