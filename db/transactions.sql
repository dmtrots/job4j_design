CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    owner VARCHAR(50),
    balance NUMERIC(10,2)
);

INSERT INTO accounts (owner, balance) VALUES
('Alice', 1000.00),
('Bob', 500.00);

SELECT * FROM accounts;

BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT balance FROM accounts WHERE owner = 'Alice';
UPDATE accounts SET balance = balance - 100 WHERE owner = 'Alice';
UPDATE accounts SET balance = balance + 100 WHERE owner = 'Bob';

BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT balance FROM accounts WHERE owner = 'Alice';
UPDATE accounts SET balance = balance - 100 WHERE owner = 'Alice';
UPDATE accounts SET balance = balance + 100 WHERE owner = 'Bob';
COMMIT;

COMMIT;