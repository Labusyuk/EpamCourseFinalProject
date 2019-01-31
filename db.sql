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
('5211453444272186', '100000', '50000','2500','5');

INSERT INTO `bankschema`.`deposite` (`account_number`, `rate`)
VALUES ('5118753421277686', '15');

INSERT INTO `bankschema`.`payments` (`account_number`, `to`, `actions`, `amount`, `description`, `date`)
('5218863124315621', '5118753421277686' , 'transaction', '50' , 'Остаток: 530 грн.', 'Thu Jan 31 14:40:00 EET 2019'),
('5118753421277686', '5218863124315621' , 'transaction', '50' , 'Остаток: 580 грн.', 'Wed Jan 30 16:18:00 EET 2019'),
('5218863124315621', '1' , 'utilities', '1500' , 'Остаток: 530 грн.', 'Wed Jan 30 16:18:00 EET 2019'),
('5168755428075126', '1' , 'transaction', '2030' , 'Остаток: 2030 грн.', 'Wed Jan 30 16:18:00 EET 2019');

Пополнения 50 грн с карты 5118 7534 2127 7686. Баланс: 580 грн.