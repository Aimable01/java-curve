package org.aimable.nestfitmvc.DAOImpl;

import org.aimable.nestfitmvc.DAO.PostDAO;
import org.aimable.nestfitmvc.model.Post;
import org.aimable.nestfitmvc.utility.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {
    private void createTableIfNotExists() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS posts(
                    post_id SERIAL PRIMARY KEY,
                    title VARCHAR(200) NOT NULL,
                    content TEXT NOT NULL,
                    author VARCHAR(100) NOT NULL,
                    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    user_id INTEGER NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
                )
                """;

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Posts table created/verified successfully");
        } catch (SQLException e) {
            System.err.println("Error creating posts table: " + e.getMessage());
            throw new SQLException("Failed to create posts table: " + e.getMessage(), e);
        }
    }

    public PostDAOImpl() throws SQLException {
        createTableIfNotExists();
        // Test connection during initialization
        Connection conn = null;
        try {
            conn = DbConnection.getConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish database connection");
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to initialize PostDAOImpl: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void createPost(Post post) throws SQLException {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO posts (title, content, author, user_id) VALUES (?,?,?,?)")) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getAuthor());
            ps.setInt(4, post.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error creating post: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM posts");
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Post post = new Post(
                    rs.getLong("post_id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("author"),
                    rs.getString("created_date"),
                    rs.getInt("user_id")
                );
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post getPostById(Long id) throws SQLException {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM posts WHERE post_id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Post(
                        rs.getLong("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getString("created_date"),
                        rs.getInt("user_id")
                    );
                }
                throw new SQLException("Post not found with id: " + id);
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving post: " + e.getMessage(), e);
        }
    }

    @Override
    public void updatePost(Post post) throws SQLException {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE posts SET title = ?, content = ?, author = ?, user_id = ? WHERE post_id = ?")) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getAuthor());
            ps.setInt(4, post.getUserId());
            ps.setLong(5, post.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Post not found with id: " + post.getId());
            }
        } catch (SQLException e) {
            throw new SQLException("Error updating post: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletePost(Long id) throws SQLException {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM posts WHERE post_id = ?")) {
            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Post not found with id: " + id);
            }
        } catch (SQLException e) {
            throw new SQLException("Error deleting post: " + e.getMessage(), e);
        }
    }
}
