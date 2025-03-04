package org.aimable.nestfitmvc.service;

import org.aimable.nestfitmvc.DAO.PostDAO;
import org.aimable.nestfitmvc.DAOImpl.PostDAOImpl;
import org.aimable.nestfitmvc.model.Post;

import java.sql.SQLException;
import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;

    public PostServiceImpl() {
        try {
            this.postDAO = new PostDAOImpl();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize PostDAO", e);
        }
    }

    @Override
    public void createPost(Post post) throws SQLException {
        postDAO.createPost(post);
    }

    @Override
    public void updatePost(Long id, Post post) throws SQLException {
        post.setId(id);
        postDAO.updatePost(post);
    }

    @Override
    public void deletePost(Long id) throws SQLException {
        postDAO.deletePost(id);
    }

    @Override
    public List<Post> getAllPosts() throws SQLException {
        return postDAO.getAllPosts();
    }

    @Override
    public Post getPostById(Long id) throws SQLException {
        return postDAO.getPostById(id);
    }
}