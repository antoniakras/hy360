CREATE TABLE IF NOT EXISTS client ( Name VARCHAR(20) , Account INT, Date DATE  ,Lim INT , Debt FLOAT(24) , Credit INT);

CREATE TABLE IF NOT EXISTS company (Name VARCHAR(20) , Account INT, Date DATE , lim INT , Debt FLOAT(24) , Credit FLOAT(24) , Employee_name VARCHAR(20), Employee_id INT);

CREATE TABLE IF NOT EXISTS retailer (Name VARCHAR(20), Account INT, Date DATE ,Commission INT , Earnings FLOAT(24) , Debt FLOAT(24));

CREATE TABLE IF NOT EXISTS transactions (Client_name VARCHAR(20) , Retailer_name VARCHAR(20) , Date DATE , Amount FLOAT(24),ID INT , Type VARCHAR(20));

CREATE TABLE products (Name VARCHAR(20), ID INT, Company VARCHAR(20), Retailer_name VARCHAR(20), Price INT);

INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Τραπεζι σαλονιου','111','IKEA','11','100');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Κρεβατι','112','IKEA','12','180');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Πολυθρονα','113','IKEA','13','70');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Καναπες','114','IKEA','12','180');


INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Τηλεοραση','211','Public','21','250');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Βιβλιο','212','Public','23','25');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('Οθονη','213','Public','22','150');
INSERT INTO products (Name,ID,Company,Retailer_id,Price) VALUES ('PS5','214','Public','24','500');


INSERT INTO client (Name,Account,Date,Lim,Debt,Credit) VALUES ('Antonia Krasoudaki','211','25-01-2022','300','0','1000');
INSERT INTO client (Name,Account,Date,Lim,Debt,Credit) VALUES ('Angelos Rosmarakis','212','25-01-2022','200','0','1500');
INSERT INTO client (Name,Account,Date,Lim,Debt,Credit) VALUES ('Regina Madi','213','25-01-2022','400','0','1700');
INSERT INTO client (Name,Account,Date,Lim,Debt,Credit) VALUES ('Eleni Maragakaki','214','25-01-2022','500','0','1600');
INSERT INTO client (Name,Account,Date,Lim,Debt,Credit) VALUES ('Katerina','215','25-01-2022','500','0','1700');

INSERT INTO company (Name,Account,Date,lim,Debt,Credit,Employee_name,Employee_id) VALUES ('IKEA','215','25-01-2022','1000','0','16000','Markos Antoniou','111');
INSERT INTO company (Name,Account,Date,lim,Debt,Credit,Employee_name,Employee_id) VALUES ('Public','216','25-01-2022','1000','0','14000','Antonia Antoniou','112');
INSERT INTO company (Name,Account,Date,lim,Debt,Credit,Employee_name,Employee_id) VALUES ('Kotsobolos','217','25-01-2022','1000','0','15000','Maria Paraskaki','113');
INSERT INTO company (Name,Account,Date,lim,Debt,Credit,Employee_name,Employee_id) VALUES ('Lidl','218','25-01-2022','1000','0','18000','Ilias Maurotsoukalos','114');
INSERT INTO company (Name,Account,Date,lim,Debt,Credit,Employee_name,Employee_id) VALUES ('Zara','219','25-01-2022','1000','0','19000','Giorgos Xairetis','115');

INSERT INTO retailer (Name,Account,Date,Commission,Earnings,Debt) VALUES ('Markos Antoniou','220','25-01-2022','5','0','0');
INSERT INTO retailer (Name,Account,Date,Commission,Earnings,Debt) VALUES ('Antonia Antoniou','221','25-01-2022','5','0','0');
INSERT INTO retailer (Name,Account,Date,Commission,Earnings,Debt) VALUES ('Maria Paraskaki','222','25-01-2022','5','0','0');
INSERT INTO retailer (Name,Account,Date,Commission,Earnings,Debt) VALUES ('Ilias Maurotsoukalos','223','25-01-2022','5','0','0');
INSERT INTO retailer (Name,Account,Date,Commission,Earnings,Debt) VALUES ('Giorgos Xairetis','225','25-01-2022','5','0','0');

INSERT INTO transactions(Client_name,Retailer_name,Date,Amount,ID,Type) VALUES ('Antonia Krasoudaki','Markos Antoniou','25-01-2022','100','111','Purchase');
INSERT INTO transactions(Client_name,Retailer_name,Date,Amount,ID,Type) VALUES ('Angelos Rosmarakis','Antonia Antoniou','25-01-2022','250','211','Purchase');
INSERT INTO transactions(Client_name,Retailer_name,Date,Amount,ID,Type) VALUES ('Regina Madi','Antonia Antoniou','25-01-2022','25','212','Purchase');
INSERT INTO transactions(Client_name,Retailer_name,Date,Amount,ID,Type) VALUES ('Angelos Rosmarakis','Antonia Antoniou','25-01-2022','150','213','Return');
INSERT INTO transactions(Client_name,Retailer_name,Date,Amount,ID,Type) VALUES ('Eleni Maragakaki','Markos Antoniou','25-01-2022','70','113','Return');