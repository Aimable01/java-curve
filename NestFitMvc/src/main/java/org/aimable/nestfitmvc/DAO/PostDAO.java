package org.aimable.nestfitmvc.DAO;

import org.aimable.nestfitmvc.model.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDAO {
    void createPost(Post post) throws SQLException;
    List<Post> getAllPosts();
    Post getPostById(Long id) throws SQLException;
    void updatePost(Post post) throws SQLException;
    void deletePost(Long id) throws SQLException;
}
