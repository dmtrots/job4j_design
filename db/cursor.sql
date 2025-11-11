BEGIN;
DECLARE cursor_products SCROLL CURSOR FOR
SELECT * FROM products ORDER BY id;

FETCH LAST FROM cursor_products;

MOVE BACKWARD 6 FROM cursor_products;
FETCH FROM cursor_products;

MOVE BACKWARD 9 FROM cursor_products;
FETCH FROM cursor_products;

MOVE BACKWARD 6 FROM cursor_products;
FETCH FROM cursor_products;

MOVE BACKWARD 2 FROM cursor_products;
FETCH FROM cursor_products;

CLOSE cursor_products;
COMMIT;