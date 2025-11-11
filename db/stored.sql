CREATE OR REPLACE FUNCTION delete_product_by_id(p_id integer)
RETURNS integer
LANGUAGE 'plpgsql'
AS
$$
DECLARE
    deleted_count integer;
BEGIN
    DELETE FROM products
    WHERE id = p_id
    RETURNING id INTO deleted_count;

    IF deleted_count IS NULL THEN
        deleted_count := 0;
    END IF;
    RETURN deleted_count;
END;
$$

SELECT delete_product_by_id(3);
select * from products;

CREATE OR REPLACE PROCEDURE delete_products_with_zero_count()
LANGUAGE plpgsql
AS
$$
	BEGIN
	    DELETE FROM products
	    WHERE count = 0;
	END;
$$;

CALL delete_products_with_zero_count();
select * from products;