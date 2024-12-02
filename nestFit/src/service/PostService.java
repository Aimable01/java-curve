package service;

import database.DbConn;
import models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostService {

    public void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS posts (
                    post_id SERIAL PRIMARY KEY,
                    user_id INT NOT NULL,
                    content TEXT NOT NULL,
                    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
                )
                """;

        try (
                Connection conn = DbConn.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(sql);
            System.out.println("Post table ensured to exist");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPost(int userId, String content) {
        String sql = "INSERT INTO posts(user_id, content) VALUES(?, ?)";

        try (
                Connection conn = DbConn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, userId);
            stmt.setString(2, content);
            stmt.executeUpdate();
            System.out.println("Post added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";

        try (
                Connection conn = DbConn.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("post_id"),
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getTimestamp("created")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public Post getPostById(int postId) {
        String sql = "SELECT * FROM posts WHERE post_id = ?";
        Post post = null;

        try (
                Connection conn = DbConn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, postId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    post = new Post(
                            rs.getInt("post_id"),
                            rs.getInt("user_id"),
                            rs.getString("content"),
                            rs.getTimestamp("created")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }
}
