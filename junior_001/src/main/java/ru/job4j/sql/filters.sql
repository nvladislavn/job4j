DROP TABLE IF EXISTS stock, products, types;

CREATE TABLE types
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE products
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(200) NOT NULL,
    type_id      INT REFERENCES types (id),
    expired_date date,
    price        money
);

CREATE TABLE stock
(
    product_id INT REFERENCES products (id) PRIMARY KEY,
    quantity   INT DEFAULT 0
);

INSERT INTO types(name)
VALUES ('MILK'),
       ('CHEESE'),
       ('BREAD'),
       ('VEGETABLE'),
       ('FRUIT'),
       ('ALCOHOL'),
       ('BOOK'),
       ('NEWSPAPER'),
       ('ICE CREAM');

INSERT INTO products(name, type_id, expired_date, price)
VALUES ('MILK 2,5', (SELECT id FROM types WHERE types.name = 'MILK'), '2019-10-01', 55.00),
       ('MILK 3,2', (SELECT id FROM types WHERE types.name = 'MILK'), '2019-10-01', 65.00),
       ('COUNTRY CHEESE', (SELECT id FROM types WHERE types.name = 'CHEESE'), '2019-11-01', 190.00),
       ('MOZZARELLA', (SELECT id FROM types WHERE types.name = 'CHEESE'), '2019-12-01', 375.00),
       ('HOCHLAND', (SELECT id FROM types WHERE types.name = 'CHEESE'), '2020-02-01', 250.00),
       ('ICE CREAM 48 cents', (SELECT id FROM types WHERE types.name = 'ICE CREAM'), '2020-01-15', 50.00),
       ('33 penguins ICE CREAM', (SELECT id FROM types WHERE types.name = 'ICE CREAM'), '2019-10-31', 350.00),
       ('BORODINO', (SELECT id FROM types WHERE types.name = 'BREAD'), '2019-09-25', 35.00),
       ('APPLE CRISP', (SELECT id FROM types WHERE types.name = 'FRUIT'), '2019-10-20', 280.00),
       ('PC MAGAZINE', (SELECT id FROM types WHERE types.name = 'NEWSPAPER'), '2100-01-01', 250.00),
       ('CHAMPAGNE', (SELECT id FROM types WHERE types.name = 'ALCOHOL'), '2021-12-01', 750.00),
       ('BRANDY', (SELECT id FROM types WHERE types.name = 'ALCOHOL'), '2021-11-01', 750.00);

INSERT INTO stock(product_id, quantity)
VALUES ((SELECT id FROM products p WHERE p.name = 'MILK 2,5'), 25),
       ((SELECT id FROM products p WHERE p.name = 'MILK 3,2'), 20),
       ((SELECT id FROM products p WHERE p.name = 'COUNTRY CHEESE'), 6),
       ((SELECT id FROM products p WHERE p.name = 'MOZZARELLA'), 8),
       ((SELECT id FROM products p WHERE p.name = 'HOCHLAND'), 15),
       ((SELECT id FROM products p WHERE p.name = 'ICE CREAM 48 cents'), 25),
       ((SELECT id FROM products p WHERE p.name = '33 penguins ICE CREAM'), 3),
       ((SELECT id FROM products p WHERE p.name = 'BORODINO'), 40),
       ((SELECT id FROM products p WHERE p.name = 'APPLE CRISP'), 30),
       ((SELECT id FROM products p WHERE p.name = 'PC MAGAZINE'), 5),
       ((SELECT id FROM products p WHERE p.name = 'CHAMPAGNE'), 45),
       ((SELECT id FROM products p WHERE p.name = 'BRANDY'), 45);

--1. Select products with type 'CHEESE'
SELECT *
FROM products
WHERE type_id = (SELECT id FROM types WHERE types.name = 'CHEESE');

--2. Select products that have the words "ICE CREAM" in their name.
SELECT *
FROM products as p
WHERE p.name LIKE '%ICE CREAM%';

--3. Select products whose expiration date expires next month.
SELECT *
FROM products
WHERE expired_date BETWEEN
          (date_trunc('month', current_date + '1 month'::INTERVAL)) AND
          (date_trunc('month', current_date + '2month'::INTERVAL) - '1 second'::INTERVAL);

--4. SELECT the most expensive product.
SELECT *
FROM products as p
WHERE price = (SELECT MAX(price) FROM products);

--5. SELECT the quantity of specified product.
SELECT SUM(quantity)
FROM stock
WHERE product_id IN
      (SELECT id
       FROM products
       WHERE type_id =
             (SELECT id FROM types WHERE name = 'CHEESE'));

--6. SELECT all products with types 'MILK' and 'CHEESE'.
SELECT *
FROM products
WHERE type_id IN
      (SELECT id
       FROM types t
       WHERE t.name = 'MILK'
          OR t.name = 'CHEESE');

--7.SELECT products with quantity less than 10.
--var 1.
SELECT p.id, p.name
FROM products p
WHERE p.id IN (SELECT product_id FROM stock WHERE quantity < 10);

--var. 2
SELECT p.id, p.name, s.quantity
FROM products p
         INNER JOIN stock s
                    on p.id = s.product_id
WHERE p.id IN
      (SELECT product_id FROM stock WHERE quantity < 10)
ORDER BY s.quantity;

--8. SELECT all products with their types.
SELECT p.id idProd, p.name prodName, t.id idType, t.name typeName
FROM products p
         LEFT JOIN types t on p.type_id = t.id
ORDER BY t.id;

