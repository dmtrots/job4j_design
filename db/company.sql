CREATE TABLE companies
(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    CONSTRAINT companies_pkey PRIMARY KEY (id)
);

CREATE TABLE people
(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    company_id INTEGER REFERENCES companies(id),
    CONSTRAINT people_pkey PRIMARY KEY (id)
);

INSERT INTO companies (id, name) VALUES
(1, 'Google'),
(2, 'Amazon'),
(3, 'Microsoft'),
(4, 'Apple'),
(5, 'Facebook'),
(6, 'Netflix');

INSERT INTO people (id, name, company_id) VALUES
(1, 'Alice', 1),
(2, 'Bob', 2),
(3, 'Charlie', 3),
(4, 'David', 5),     -- в компании с id = 5
(5, 'Eve', 2),
(6, 'Frank', 3),
(7, 'Grace', 1),
(8, 'Carl', 4),
(9, 'Ivan', NULL),   -- без компании
(10, 'Judy', 6),
(11, 'Mallory', 6),
(12, 'Jennifer', 6);

SELECT p.name person_name,
       c.name company_name
FROM people p
JOIN companies c ON p.company_id = c.id
WHERE p.company_id != 5
   OR p.company_id IS NULL;

SELECT c1.name,
	COUNT(p.id) employee_count
FROM companies c1
LEFT JOIN people p ON p.company_id = c1.id
GROUP BY c1.id, c1.name
HAVING COUNT(p.id) = (
    SELECT MAX(cnt)
    FROM (
        SELECT COUNT(1) cnt
        FROM companies c2
        LEFT JOIN people p2 ON p2.company_id = c2.id
        GROUP BY c2.id
    ) t
);