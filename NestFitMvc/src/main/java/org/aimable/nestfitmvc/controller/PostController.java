package org.aimable.nestfitmvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aimable.nestfitmvc.model.Post;
import org.aimable.nestfitmvc.service.PostService;
import org.aimable.nestfitmvc.service.PostServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/posts/*")
public class PostController extends HttpServlet {
    private PostService postService;

    @Override
    public void init() throws ServletException {
        super.init();
        postService = new PostServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // List all posts
            List<Post> posts = postService.getAllPosts();
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/WEB-INF/views/posts/list.jsp").forward(request, response);
        } else if (pathInfo.equals("/edit")) {
            // Handle edit request
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Post ID is required");
                return;
            }
            try {
                Long id = Long.parseLong(idStr);
                Post post = postService.getPostById(id);
                if (post == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");
                    return;
                }
                request.setAttribute("post", post);
                request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID format");
            }
        } else {
            // Get single post
            String[] splits = pathInfo.split("/");
            if (splits.length != 2) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            Long id = Long.parseLong(splits[1]);
            Post post = postService.getPostById(id);
            request.setAttribute("post", post);
            request.getRequestDispatcher("/WEB-INF/views/posts/detail.jsp").forward(request, response);
        }
        } catch (SQLException e) {
            throw new ServletException("Database error occurred", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            if (pathInfo != null && pathInfo.equals("/create")) {
                // Get user information from session
                String userEmail = (String) request.getSession().getAttribute("userEmail");
                Integer userId = (Integer) request.getSession().getAttribute("userId");
                
                if (userEmail == null || userId == null) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    return;
                }
                
                // Create new post
                Post post = new Post();
                post.setTitle(request.getParameter("title"));
                post.setContent(request.getParameter("content"));
                post.setAuthor(userEmail);
                post.setUserId(userId);
                
                postService.createPost(post);
                
                // Get updated posts list and set it as request attribute
                List<Post> posts = postService.getAllPosts();
                request.getSession().setAttribute("posts", posts);
                
                // Redirect back to dashboard
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else if (pathInfo.equals("/update")) {
                // Check user authentication
                String userEmail = (String) request.getSession().getAttribute("userEmail");
                Integer userId = (Integer) request.getSession().getAttribute("userId");
                
                if (userEmail == null || userId == null) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    return;
                }
                
                // Update existing post
                String idStr = request.getParameter("id");
                if (idStr == null || idStr.trim().isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Post ID is required");
                    return;
                }
                
                try {
                    Long id = Long.parseLong(idStr);
                    // First get the existing post to verify ownership
                    Post existingPost = postService.getPostById(id);
                    if (existingPost == null) {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");
                        return;
                    }
                    
                    // Verify post ownership
                    if (!userId.equals(existingPost.getUserId())) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "You don't have permission to edit this post");
                        return;
                    }
                    
                    Post post = new Post();
                    post.setId(id);
                    post.setTitle(request.getParameter("title"));
                    post.setContent(request.getParameter("content"));
                    post.setAuthor(userEmail);
                    post.setUserId(userId);
                    
                    postService.updatePost(id, post);
                    response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID format");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid path");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error occurred", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        // Update existing post
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long id = Long.parseLong(splits[1]);
        Post post = new Post();
        post.setTitle(request.getParameter("title"));
        post.setContent(request.getParameter("content"));
        
        postService.updatePost(id, post);
        response.sendRedirect(request.getContextPath() + "/posts");
        } catch (SQLException e) {
            throw new ServletException("Database error occurred", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        // Delete post
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long id = Long.parseLong(splits[1]);
        postService.deletePost(id);
        response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new ServletException("Database error occurred", e);
        }
    }
}
