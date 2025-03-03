package org.aimable.nestfitmvc.DAOImpl;

import org.aimable.nestfitmvc.DAO.UserDAO;
import org.aimable.nestfitmvc.model.User;
import org.aimable.nestfitmvc.utility.DbConnection;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users(
                    user_id SERIAL PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL
                )
                """;

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Users table ensured to exist");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDAOImpl() throws SQLException {
        createTableIfNotExists();
        // Test connection during initialization
        Connection conn = null;
        try {
            conn = DbConnection.getConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish database connection");
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to initialize UserDAOImpl: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Log the error but don't throw as the main operation is done
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void registerUser(User user) throws SQLException {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, email, password) VALUES (?,?,?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error registering user: " + e.getMessage(), e);
        }
    }

    @Override
    public void login(String email, String password) throws SQLException {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Invalid email or password");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error during login: " + e.getMessage(), e);
        }
    }
}

