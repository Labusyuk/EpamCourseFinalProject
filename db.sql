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

INSERT INTO `bankschema`.`users` (`login`, `password`, `role`,`name_first`,`name_last`)
VALUES ('Labusyuk', '1604612', 'USER','Vitaliy','Labusyuk');

INSERT INTO `bankschema`.`accounts` (`number`, `type`, `owner`,`balance`,`validity`)
VALUES ('5168755428075126', '0', 'Labusyuk','35000','30-10-2024'),
('5118753421277686', '2', 'Labusyuk','100000','11-05-2020'),
('5218863124315621', '1', 'Labusyuk','34112','25-02-2022'),
('5211453444272186', '1', 'Labusyuk','13227','18-06-12');

INSERT INTO `bankschema`.`credite` (`account_number`, `limit`, `debt`,`amount_interest`,`rate`)
VALUES ('5218863124315621', '25000', '12450','249','2'),
VALUES ('5211453444272186', '100000', '50000','2500','5');

INSERT INTO `bankschema`.`credite` (`account_number`, `rate`)
VALUES ('5118753421277686', '15');