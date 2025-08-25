CREATE TABLE IF NOT EXISTS client ( Name VARCHAR(20) , Account INT ,Lim INT , Debt FLOAT(24) , Credit INT);

CREATE TABLE IF NOT EXISTS company (Name VARCHAR(20) , Account INT, Date DATE , lim INT , Debt FLOAT(24) , Credit FLOAT(24) , Employee_name VARCHAR(20), Employee_id INT);

CREATE TABLE IF NOT EXISTS retailer (Name VARCHAR(20), Account INT, Date DATE ,Commission FLOAT(24) , Earnings FLOAT(24) , Debt FLOAT(24));

CREATE TABLE IF NOT EXISTS transactions (Client_name VARCHAR(20) , Retailer_name VARCHAR(20) , Date DATE , Amount FLOAT(24) , Type VARCHAR(20));

CREATE TABLE IF NOT EXISTS products (Name VARCHAR(20) , ID INT , Company VARCHAR(20) , Retailer_id INT, Price INT);

INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Τραπεζι σαλονιου','111','IKEA','11','100');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Κρεβατι','112','IKEA','12','180');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Πολυθρονα','113','IKEA','13','70');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Καναπες','114','IKEA','12','180');


INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Τηλεοραση','211','Public','21','250');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Βιβλιο','212','Public','23','25');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Οθονη','213','Public','22','150');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('PS5','214','Public','24','500');