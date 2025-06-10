-- V2__insert_bulk_students.sql
DO $$
DECLARE
i INTEGER := 0;
BEGIN
    WHILE i < 500 LOOP
        INSERT INTO student (first_name, last_name, age, email)
        VALUES (
            'John' || i,
            'Doe' || i,
            18 + floor(random() * 5),
            'john' || i || '.doe@example.com'
        );
        i := i + 1;
END LOOP;
END $$;