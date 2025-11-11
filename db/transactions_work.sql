CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    position VARCHAR(50)
);

BEGIN;

INSERT INTO employees (name, position)
VALUES ('Alice', 'Manager'),
       ('Bob', 'Developer');

SELECT * FROM employees;

SAVEPOINT sp1;

INSERT INTO employees (name, position)
VALUES ('Charlie', 'Tester');

SAVEPOINT sp2;

UPDATE employees SET position = 'director' where name = 'Charlie';

ROLLBACK TO sp2;

SELECT * FROM employees;

ROLLBACK TO sp1;

SELECT * FROM employees;

COMMIT;

