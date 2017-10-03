CREATE TABLE category (
	
	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

INSERT INTO category (name, description,image_url,is_active) VALUES ('Men', 'Mens wear!', 'CAT_1.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Women', 'womens wear!', 'CAT_2.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('kids', 'kids wear!', 'CAT_3.png', true);

CREATE TABLE user_detail (
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id),
);


INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Subash', 'V' , 'ADMIN' , 'true' ,'	$2a$06$bq4IPFGtJ.2zioWcKknWyeP5gikVOpxnnAMTrbian8w.tny/iNLby' , ' subashcbose360@gmail.com' , ' 7358698336');

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Pavithra' , ' Pappu ' , ' SUPPLIER ' , ' true'	, ' $2a$06$6rR7NA8cEkqsJJSKIidsoePfzngwv04F0XF3Oicfj/nRaqdlOLoWW ' , ' pavithras@gmail.com ' , '123456789');

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Arjun', 'Krishna' , 'USER' , 'true' , '	$2a$06$6e1jXCtaF5f8M0/LVHW3W.m2PPqmc6Q7UfGcFQvI6FCzW9kQRmaHa','	ak@gmail.com','	123456');



CREATE TABLE product (
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id),	
);	


INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('A11', 'T Shirt', 'adidas', 'branded mens shirt!', 800, 5, true, 3, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('A12', 'T Shirt', 'nike', 'branded mens shirt!', 750, 2, true, 3, 3 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('A13', 'T Shirt', 'polo', 'branded mens shirt!',900, 5, true, 3, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('B11', ' Saree', 'cavalli', 'Womens Sarees', 6000, 3, true, 1, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('B12', 'Shirt', 'john', 'branded womens shirt!',1200, 5, true, 1, 3 );




CREATE TABLE address (
	id IDENTITY,
	user_id int,
	address_line_one VARCHAR(100),
	address_line_two VARCHAR(100),
	city VARCHAR(20),
	state VARCHAR(20),
	country VARCHAR(20),
	postal_code VARCHAR(10),
	is_billing BOOLEAN,
	is_shipping BOOLEAN,
	CONSTRAINT fk_address_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_address_id PRIMARY KEY (id)
);

-- the cart table to store the user cart top-level details
CREATE TABLE cart (
	id IDENTITY,
	user_id int,
	grand_total DECIMAL(10,2),
	cart_lines int,
	CONSTRAINT fk_cart_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_cart_id PRIMARY KEY (id)
);


-- adding a supplier correspondece address
INSERT INTO address( user_id, address_line_one, address_line_two, city, state, country, postal_code, is_billing, is_shipping) 
VALUES (2, '102 Sabarmati Society, Mahatma Gandhi Road', 'Near Salt Lake, Gandhi Nagar', 'Ahmedabad', 'Gujarat', 'India', '111111', true, false );



-- adding a cart for testing 
INSERT INTO cart (user_id, grand_total, cart_lines) VALUES (null, 0, 0);




-- the acrt line table to store the cart details


CREATE TABLE cart_line (
	id IDENTITY,
	cart_id int,
	total DECIMAL(10,2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10,2),
	is_available boolean,
	CONSTRAINT fk_cartline_cart_id FOREIGN KEY (cart_id) REFERENCES cart(id),
	CONSTRAINT fk_cartline_product_id FOREIGN KEY (product_id) REFERENCES product(id),
	CONSTRAINT pk_cartline_id PRIMARY KEY (id)
);

