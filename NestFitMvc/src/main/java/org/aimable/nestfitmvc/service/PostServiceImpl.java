package org.aimable.nestfitmvc.service;

import org.aimable.nestfitmvc.model.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService {
    private List<Post> posts;

    public PostServiceImpl() {
        this.posts = new ArrayList<>();
    }

    @Override
    public void createPost(Post post) throws SQLException {
        // Implement post creation logic
        posts.add(post);
    }

    @Override
    public void updatePost(Long id, Post post) throws SQLException {
        // Implement post update logic
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId().equals(id)) {
                post.setId(id);
                posts.set(i, post);
                break;
            }
        }
    }

    @Override
    public void deletePost(Long id) throws SQLException {
        // Implement post deletion logic
        posts.removeIf(post -> post.getId().equals(id));
    }

    @Override
    public List<Post> getAllPosts() throws SQLException {
        // Implement get all posts logic
        return new ArrayList<>(posts);
    }

    @Override
    public Post getPostById(Long id) throws SQLException {
        // Implement get post by ID logic
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}