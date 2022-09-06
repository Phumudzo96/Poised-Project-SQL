DROP DATABASE IF EXISTS `PoisePMS`;
CREATE DATABASE `PoisePMS`;
USE `PoisePMS`;

CREATE TABLE `project_manager`(
`projectNum` int(3),
`projectName` varchar(255),
`buliding_type` varchar(255),
`address` varchar(255),
`erfNum` int(4),
`totalCost` decimal(9,2),
`paid` decimal(9,2),
`deadline` date,
`completion` varchar(255),
`status` varchar(255),
PRIMARY KEY(`projectNum`)
);
INSERT INTO `project_manager`
VALUES (11, "Micheal Lin", "House", "25th Almornt ave", 2211, 2500000.00, 150000.00, "2022-12-20", "overdue", "incomplete"),
(23, "Phumudzo Mbelengwa", "House", "Eye of Africa estate",2223, 5000000.00, 1250000.00,"2025-01-06", "under-constraction", "incomplete");

CREATE TABLE `constractor`(
`name` varchar(255),
`number` int(10),
`email` varchar(255),
`address` varchar(255),
PRIMARY KEY(name)
);

INSERT INTO `constractor`
VALUES ("James Martins", 0836771005, "jamesmartins@gmail.com", "21st Open road Street"),
("Andile Twala", 0715321880, "andiletwala@gmail.com", "wild peach street");

CREATE TABLE `customer`(
`name` varchar(255),
`number` int(10),
`email` varchar(255),
`address` varchar(255),
PRIMARY KEY(name)
);

INSERT INTO `customer`
VALUES("Mary Khumalo", 0826489339, "marykhumalo@gmail.com", "51 cheatnut road"),
("Ben September", 0636060678, "benseptember@gmail.com", "106 cheatnut road");