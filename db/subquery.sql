CREATE TABLE customers
(
    id         SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    age        INT,
    country    TEXT
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES
('Ivan', 'Ivanov', 25, 'Russia'),
('Petr', 'Petrov', 30, 'Russia'),
('Anna', 'Smirnova', 22, 'Belarus'),
('Olga', 'Kuznetsova', 22, 'Kazakhstan'),
('John', 'Doe', 40, 'USA');

SELECT *
FROM customers
WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    amount      INT,
    customer_id INT REFERENCES customers (id)
);

INSERT INTO orders (amount, customer_id)
VALUES
(100, 1),
(250, 3);

SELECT *
FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);