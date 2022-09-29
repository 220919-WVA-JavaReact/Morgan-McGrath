CREATE TABLE inventory(
	id int PRIMARY KEY,
	"name" varchar(25),
	price decimal(5,2),
	description varchar(225),
	quantity int
);

INSERT INTO inventory 
VALUES (
	1001, 
	'Hershey Bar',
	3.45,
	'A small chocolate bar',
	50
);

INSERT INTO inventory 
VALUES
	(1002, 'Skittles', 3.05, 'A bag of rainbow colored candies', 100);

INSERT INTO inventory
VALUES
	(2003, 'Gummy Bears', 5.55, 'A large bag of chewy fruit-flavored bears', 48),
	(2005, 'Sour Gummy Worms', 9.55, 'A very large bag of gelatin worms', 20),
	(3008, 'Lollipop', 2.25, 'A hard candy atop a stick', 268),
	(9007, 'M&Ms', 1.95, 'A bag of small chocolate candies', 58);

SELECT * FROM inventory;
	
UPDATE inventory SET quantity = 48
WHERE ID = 1001;

SELECT description FROM inventory;

SELECT "name", price FROM inventory;

UPDATE inventory SET description = 'A very large, delicious bag of worms'
WHERE id = 2005;

UPDATE inventory SET quantity = 100
WHERE id = 9007;

UPDATE inventory SET price = 2.50
WHERE id = 3008;

CREATE TABLE employees(
	id int,
	"name" varchar(50),
	favorite_candy int REFERENCES inventory
);

INSERT INTO employees
VALUES 
	(4001, 'Willy Wonka', 2005),
	(4002, 'Milton Hershey', 1001),
	(4003, 'Franklin Mars', 9007),
	(4004, 'John Cadbury', NULL);
	
SELECT e."name", i."name"
FROM employees e
LEFT JOIN inventory i
ON e.favorite_candy = i.id;

SELECT e."name", i."name"
FROM employees e
RIGHT JOIN inventory i 
ON e.favorite_candy = i.id;

SELECT price * quantity
FROM inventory 
WHERE id = 9007;

SELECT "name", price AS Smallest_Price
FROM inventory
WHERE price = min(price);
