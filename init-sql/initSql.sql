create schema if not exists CPU_DB default character set utf8;
CREATE TABLE `USERS` (
  `ID` VARCHAR(11) NOT NULL,
  `name` VARCHAR(255) NULL ,
  `mobile` VARCHAR(64) NULL,
  `birthDate` DATE NULL,
  `email` VARCHAR(64) NULL,
  `password` VARCHAR(255) NULL,
  PRIMARY KEY (`ID`));


CREATE TABLE `PERMISSION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  PRIMARY KEY (`id`))
COMMENT = 'permissions data table ';

CREATE TABLE `USER_PERMISSION` (
  `userId` VARCHAR(11) NOT NULL,
  `permissionId` INT NOT NULL,
  INDEX `user_FK_idx` (`userId` ASC),
  INDEX `permissions_FK_idx` (`permissionId` ASC),
  CONSTRAINT `user_FK`
    FOREIGN KEY (`userId`)
    REFERENCES `cpu_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `permissions_FK`
    FOREIGN KEY (`permissionId`)
    REFERENCES `cpu_db`.`permission` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'table for permissions data for each user';


ALTER TABLE USER_PERMISSION ADD UNIQUE unique_user_permission_couple (userId, permissionId);



CREATE TABLE `cpu_db`.`HOSPITAL` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  `address` VARCHAR(128) NULL,
  `phoneNumber` VARCHAR(64) NULL,
  PRIMARY KEY (`id`))
COMMENT = 'hospitals table ';


CREATE TABLE `HOSPITAL_USERS` (
  `userId` VARCHAR(11) NOT NULL,
  `hospitalId` INT NOT NULL,
  INDEX `users_id_FK_idx` (`userId` ASC),
  INDEX `hospital_id_FK_idx` (`hospitalId` ASC),
  CONSTRAINT `users_id_FK`
    FOREIGN KEY (`userId`)
    REFERENCES `cpu_db`.`users` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `hospital_id_FK`
    FOREIGN KEY (`hospitalId`)
    REFERENCES `cpu_db`.`hospital` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'hospitals table ';

ALTER TABLE HOSPITAL_USERS ADD UNIQUE  UNIQE_USER_HOSPITAL_COUPLE (userId, hospitalId);
