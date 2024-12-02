import controller.PostController;
import controller.UserController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        PostController postController = new PostController();

        while (true) {
            System.out.println("\nWelcome! Choose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> userController.registerUser(scanner);
                case 2 -> {
                    if (userController.loginUser(scanner)) {
                        while (true) {
                            System.out.println("\nUser Menu:");
                            System.out.println("1. Create a post");
                            System.out.println("2. View all posts");
                            System.out.println("3. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (userChoice) {
                                case 1 -> postController.createPost(userController.getCurrentUser().getId(), scanner);
                                case 2 -> postController.viewAllPosts();
                                case 3 -> {
                                    System.out.println("Logging out...");
                                    break;
                                }
                                default -> System.out.println("Invalid choice. Please try again.");
                            }
                            if (userChoice == 3) break;
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
