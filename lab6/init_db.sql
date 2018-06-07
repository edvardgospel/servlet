CREATE SCHEMA `servlet_db`;

CREATE TABLE `servlet_db`.`user` (
  `id` Integer NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `usercol_UNIQUE` (`username` ASC));

CREATE TABLE `servlet_db`.`game` (
  `id` Integer NOT NULL AUTO_INCREMENT,
  `user_id` Integer NULL,
  `percentage` Double NULL,
  `difficulty` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `servlet_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO user (id,username,password)
VALUES (1,'edvard','password');

INSERT INTO user (id,username,password)
VALUES (2,'jani','password');

INSERT INTO user (id,username,password)
VALUES (3,'bela','password');