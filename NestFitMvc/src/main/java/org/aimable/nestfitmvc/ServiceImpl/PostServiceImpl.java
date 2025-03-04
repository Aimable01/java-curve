package org.aimable.nestfitmvc.ServiceImpl;

import org.aimable.nestfitmvc.DAO.PostDAO;
import org.aimable.nestfitmvc.DAOImpl.PostDAOImpl;
import org.aimable.nestfitmvc.model.Post;
import org.aimable.nestfitmvc.service.PostService;

import java.sql.SQLException;
import java.util.List;

public class PostServiceImpl implements PostService {

    private PostDAO postDAO = new PostDAOImpl();

    public PostServiceImpl() throws SQLException {
    }

    @Override
    public void createPost(Post post) throws SQLException {
        postDAO.createPost(post);
    }

    @Override
    public void updatePost(Post post) throws SQLException {
        postDAO.updatePost(post);
    }

    @Override
    public void deletePost(int id) throws SQLException {
        postDAO.deletePost(id);
    }

    @Override
    public List<Post> getAllPosts() throws SQLException {
        return postDAO.getAllPosts();
    }
}
