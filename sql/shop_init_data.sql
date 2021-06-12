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


-- Insert data
-- member
insert into member (member_id, user_name, city, street, zip_code)
VALUES (1, 'Chris Pine', '서울시', '어긴가', '1234');
insert into member (member_id, user_name, city, street, zip_code)
VALUES (2, 'Tom Hardy', '인천시', '바닷가', '1234');

-- item
INSERT into item (dtype, item_id, item_name, price, stck_qnty, actor, director, author, isbn)
VALUES ('A', 1, 'peaches', 10000, 10, NULL, NULL, 'Bieber', null );
INSERT into item (dtype, item_id, item_name, price, stck_qnty, actor, director, author, isbn)
VALUES ('A', 2, 'teenage dream', 9999, 9, NULL, NULL, 'Perry', null );
INSERT into item (dtype, item_id, item_name, price, stck_qnty, actor, director, author, isbn)
VALUES ('A', 3, 'purpose', 5000, 30, NULL, NULL, 'Bieber', null );
INSERT into item (dtype, item_id, item_name, price, stck_qnty, actor, director, author, isbn)
VALUES ('M', 4, 'Star trek', 15000, 20, 'Chris Pine', 'JJ', NULL, null );

-- delivery
INSERT into delivery (delivery_id, city, street, zip_code, STATUS)
VALUES (1, '서울시', '1번가', NULL, 'READY');
INSERT into delivery (delivery_id, city, street, zip_code, STATUS)
VALUES (2, '서울시', '2번가', NULL, 'READY');
INSERT into delivery (delivery_id, city, street, zip_code, STATUS)
VALUES (3, '서울시', '3번가', NULL, 'READY');

-- orders
INSERT into orders (order_id, order_date, order_status, delivery_id, member_id)
VALUES (1, NOW(), 'ORDER', 1, 1);
INSERT into orders (order_id, order_date, order_status, delivery_id, member_id)
VALUES (2, NOW(), 'CANCEL', 2, 1);
INSERT into orders (order_id, order_date, order_status, delivery_id, member_id)
VALUES (3, NOW(), 'ORDER', 3, 2);

-- order_item
INSERT into order_item (order_item_id, order_count, order_price, item_id, order_id)
VALUES (1, 1, 10000, 1, 1);
INSERT into order_item (order_item_id, order_count, order_price, item_id, order_id)
VALUES (2, 1, 9999, 2, 1);
INSERT into order_item (order_item_id, order_count, order_price, item_id, order_id)
VALUES (3, 2, 5000, 3, 2);
INSERT into order_item (order_item_id, order_count, order_price, item_id, order_id)
VALUES (4, 1, 15000, 4, 3);
