package com.binary_supermarket.kwizera.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        createTriggerFunction();
        createPurchaseTrigger();
    }

    private void createTriggerFunction() {
        String sql = """
            CREATE OR REPLACE FUNCTION calculate_purchase_total()
            RETURNS TRIGGER AS $$
            BEGIN
                -- Calculate total by multiplying product price with quantity
                NEW.total := (SELECT price FROM product WHERE code = NEW.product_code) * NEW.quantity;
                RETURN NEW;
            END;
            $$ LANGUAGE plpgsql;
            """;

        jdbcTemplate.execute(sql);
    }

    private void createPurchaseTrigger() {
        // Drop existing trigger if it exists
        jdbcTemplate.execute("DROP TRIGGER IF EXISTS before_purchase_insert ON purchased;");

        String createTrigger = """
            CREATE TRIGGER before_purchase_insert
            BEFORE INSERT ON purchased
            FOR EACH ROW
            EXECUTE FUNCTION calculate_purchase_total();
            """;

        jdbcTemplate.execute(createTrigger);
    }
}