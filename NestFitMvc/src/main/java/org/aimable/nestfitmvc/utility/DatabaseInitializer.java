package org.aimable.nestfitmvc.utility;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing database tables...");
        initializeTables();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup code if needed
    }

    private void initializeTables() {
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create users table if not exists
            String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    user_id SERIAL PRIMARY KEY,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    password VARCHAR(100) NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

            // Create posts table if not exists
            String createPostsTable = """
                CREATE TABLE IF NOT EXISTS posts (
                    post_id SERIAL PRIMARY KEY,
                    title VARCHAR(200) NOT NULL,
                    content TEXT NOT NULL,
                    author VARCHAR(100) NOT NULL,
                    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    user_id INTEGER NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
                )
                """;

            stmt.executeUpdate(createUsersTable);
            System.out.println("Users table created/verified successfully");

            stmt.executeUpdate(createPostsTable);
            System.out.println("Posts table created/verified successfully");

        } catch (SQLException e) {
            System.err.println("Error initializing database tables: " + e.getMessage());
            throw new RuntimeException("Failed to initialize database tables", e);
        }
    }
}