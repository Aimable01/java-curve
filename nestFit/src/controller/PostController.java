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
        System.out.println("Create an inspiring post:");
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();

        postService.insertPost(userId, content);
        System.out.println("Your post has been shared! Inspire others!");
    }

    public void viewAllPosts() {
        List<Post> posts = postService.getAllPosts();
        System.out.println("\nAll Inspiring Posts:");
        if (posts.isEmpty()) {
            System.out.println("No posts available yet.");
        } else {
            for (Post post : posts) {
                System.out.println(post);
            }
        }
    }

    public void viewMyPosts(int userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        System.out.println("\nYour Posts:");
        if (posts.isEmpty()) {
            System.out.println("You haven't shared any posts yet.");
        } else {
            for (Post post : posts) {
                System.out.println(post);
            }
        }
    }

    public void updatePost(int userId, Scanner scanner) {
        System.out.print("Enter the ID of the post you want to update: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Post post = postService.getPostById(postId);

        if (post == null || post.getUserId() != userId) {
            System.out.println("You can only update your own posts.");
            return;
        }

        System.out.print("Enter the new content for the post: ");
        String newContent = scanner.nextLine();

        postService.updatePost(postId, newContent);
        System.out.println("Your post has been updated!");
    }

    public void deletePost(int userId, Scanner scanner) {
        System.out.print("Enter the ID of the post you want to delete: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Post post = postService.getPostById(postId);

        if (post == null || post.getUserId() != userId) {
            System.out.println("You can only delete your own posts.");
            return;
        }

        postService.deletePost(postId);
        System.out.println("Your post has been deleted.");
    }
}
