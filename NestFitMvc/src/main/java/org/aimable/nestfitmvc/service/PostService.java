package org.aimable.nestfitmvc.service;

import org.aimable.nestfitmvc.model.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostService {
    void createPost(Post post) throws SQLException;
    void updatePost(Post post) throws SQLException;
    void deletePost(int id) throws SQLException;
    List<Post> getAllPosts() throws SQLException;
}
