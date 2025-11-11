CREATE OR REPLACE FUNCTION add_tax_after_insert_statement()
	RETURNS TRIGGER AS
$$
	BEGIN
	    UPDATE products
	    SET price = price * 1.10
	    WHERE id IN (SELECT id FROM inserted);
	    RETURN NULL;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trg_add_tax_after_insert
	AFTER INSERT
	ON products
	REFERENCING NEW TABLE AS
		inserted
	FOR EACH STATEMENT
	EXECUTE FUNCTION add_tax_after_insert_statement();


CREATE OR REPLACE FUNCTION add_tax_before_insert_row()
	RETURNS TRIGGER AS
$$
	BEGIN
	    NEW.price := NEW.price * 1.10;
	    RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trg_add_tax_row
	BEFORE INSERT
	ON products
	FOR EACH ROW
	EXECUTE FUNCTION add_tax_before_insert_row();

CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

CREATE OR REPLACE FUNCTION log_price_after_insert()
	RETURNS TRIGGER AS
$$
	BEGIN
	    INSERT INTO history_of_price (name, price, date)
	    VALUES (NEW.name, NEW.price, CURRENT_TIMESTAMP);
	    RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trg_log_price
	AFTER INSERT ON products
	FOR EACH ROW
	EXECUTE FUNCTION log_price_after_insert();

INSERT INTO products (name, producer, count, price)
VALUES ('Телефон', 'Samsung', 5, 1000);