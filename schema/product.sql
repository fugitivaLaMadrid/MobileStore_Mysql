#first create the:: db
CREATE DATABASE catalog;

DROP TABLE IF EXISTS catalog.product;

CREATE TABLE catalog.product (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	code VARCHAR(25),
	description VARCHAR(500),
	amount DECIMAL,
	created_at DATE,
	disabled BOOLEAN DEFAULT false
) AUTO_INCREMENT = 1;