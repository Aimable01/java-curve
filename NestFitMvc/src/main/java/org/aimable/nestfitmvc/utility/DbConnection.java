package org.aimable.nestfitmvc.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/larissa";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2006";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            // Test the connection during class initialization
            try (Connection testConn = getConnection()) {
                if (testConn == null) {
                    throw new RuntimeException("Failed to establish initial database connection");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to establish initial database connection: " + e.getMessage(), e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to database: " + e.getMessage(), e);
        }
    }
}
