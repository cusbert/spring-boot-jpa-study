CREATE TABLE IF NOT EXISTS `member` (
	`member_id` BIGINT(20) NOT NULL,
	`user_name` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`city` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`street` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`zip_code` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`member_id`)
) COLLATE='utf8_general_ci' ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `item` (
	`dtype` VARCHAR(31) NOT NULL COLLATE 'utf8_general_ci',
	`item_id` BIGINT(20) NOT NULL,
	`item_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`price` INT(11) NULL DEFAULT 0,
	`stck_qnty` INT(11) NULL DEFAULT 0,
	`actor` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`director` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`author` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`isbn` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`item_id`)
) COLLATE='utf8_general_ci' ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `delivery` (
	`delivery_id` BIGINT(20) NOT NULL,
	`city` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`street` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`zip_code` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`status` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`delivery_id`)
) COLLATE='utf8_general_ci' ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` BIGINT(20) NOT NULL,
	`order_date` DATETIME(6) NULL DEFAULT CURRENT_TIMESTAMP,
	`order_status` VARCHAR(10) NOT NULL COLLATE 'utf8_general_ci',
	`delivery_id` BIGINT(20) NOT NULL,
	`member_id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`order_id`),
	INDEX `FK_DELIVERY_DELIVERY_ID` (`delivery_id`),
	INDEX `FK_MEMBER_MEMBER_ID` (`member_id`),
	CONSTRAINT `FK_DELIVERY_DELIVERY_ID` FOREIGN KEY (`delivery_id`) REFERENCES `testdb`.`delivery` (`delivery_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_MEMBER_MEMBER_ID` FOREIGN KEY (`member_id`) REFERENCES `testdb`.`member` (`member_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
) COLLATE='utf8_general_ci' ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `order_item` (
	`order_item_id` BIGINT(20) NOT NULL,
	`order_count` INT(11) NULL DEFAULT 0,
	`order_price` INT(11) NULL DEFAULT 0,
	`item_id` BIGINT(20) NOT NULL,
	`order_id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`order_item_id`) ,
	INDEX `FK_ITEM_ITEM_ID` (`item_id`),
	INDEX `FK_ORDER_ORDER_ID` (`order_id`),
	CONSTRAINT `FK_ITEM_ITEM_ID` FOREIGN KEY (`item_id`) REFERENCES `testdb`.`item` (`item_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_ORDER_ORDER_ID` FOREIGN KEY (`order_id`) REFERENCES `testdb`.`orders` (`order_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
) COLLATE='utf8_general_ci' ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `category` (
	`category_id` BIGINT(20) NOT NULL,
	`category_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`parent_id` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`category_id`),
	INDEX `FK_CATEGORY_PARENT_ID` (`parent_id`),
	CONSTRAINT `FK_CATEGORY_PARENT_ID` FOREIGN KEY (`parent_id`) REFERENCES `testdb`.`category` (`category_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
) COLLATE='utf8_general_ci' ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `category_item` (
	`category_id` BIGINT(20) NOT NULL,
	`item_id` BIGINT(20) NOT NULL,
	INDEX `FK_ITEM_ITEM_ID` (`item_id`),
	INDEX `FK_CATEGORY_CATEGORY_ID` (`category_id`),
	CONSTRAINT `FK_CATEGORY_ID` FOREIGN KEY (`category_id`) REFERENCES `testdb`.`category` (`category_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_ITEM_ID` FOREIGN KEY (`item_id`) REFERENCES `testdb`.`item` (`item_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
) COLLATE='utf8_general_ci' ENGINE=INNODB;