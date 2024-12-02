package controller;

import models.Post;
import service.PostService;

import java.util.List;
import java.util.Scanner;

public class PostController {
    private final PostService postService;

    public PostController() {
        this.postService = new PostService();
        postService.createTableIfNotExists();
    }

    public void createPost(int userId, Scanner scanner) {
        System.out.println("Create a new post:");
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();

        postService.insertPost(userId, content);
        System.out.println("Post created successfully!");
    }

    public void viewAllPosts() {
        List<Post> posts = postService.getAllPosts();
        System.out.println("\nAll Posts:");
        if (posts.isEmpty()) {
            System.out.println("No posts available.");
        } else {
            for (Post post : posts) {
                System.out.println(post);
            }
        }
    }
}
