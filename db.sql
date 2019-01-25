CREATE TABLE `bankschema` . `users`(
`id` INT NOT NULL AUTO_INCREMENT ,
`login` VARCHAR(255) NOT NULL ,
`password` VARCHAR(100) NOT NULL ,
`role` VARCHAR(45) NOT NULL ,
`name_first` VARCHAR(45) NOT NULL ,
`name_last` VARCHAR(45) NOT NULL ,
PRIMARY KEY (`id`));

CREATE TABLE `bankschema` . `accounts`(
`id` INT NOT NULL AUTO_INCREMENT ,
`number` BIGINT NOT NULL ,
`type` SMALLINT NOT NULL ,
`owner` VARCHAR(255) NOT NULL ,
`balance` BIGINT UNSIGNED NOT NULL ,
`validity` DATE NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE `bankschema` . `credite`(
`id` INT NOT NULL AUTO_INCREMENT ,
`account_number` BIGINT NOT NULL ,
`limit` BIGINT NOT NULL ,
`debt` BIGINT NOT NULL ,
`amount_interest` BIGINT NOT NULL ,
`rate` SMALLINT NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE `bankschema` . `deposite`(
`id` INT NOT NULL AUTO_INCREMENT ,
`account_number` BIGINT NOT NULL ,
`rate` SMALLINT NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE `bankschema` . `payments`(
`id` INT NOT NULL AUTO_INCREMENT ,
`account_number` BIGINT NOT NULL ,
`to` BIGINT NULL , 
`actions` TINYINT NOT NULL ,
`amount` BIGINT NOT NULL ,
`description` VARCHAR(250) NOT NULL ,
`date` TIMESTAMP NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE `bankschema` . `pending_ap`(
`id` INT NOT NULL AUTO_INCREMENT ,
`login` VARCHAR(255) NOT NULL ,
`date` TIMESTAMP NOT NULL,
PRIMARY KEY (`id`));


