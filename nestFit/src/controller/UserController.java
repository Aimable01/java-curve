package controller;

import models.User;
import service.PostService;
import service.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private User currentUser;

    public UserController() {
        this.userService = new UserService();
        userService.createTableIfNotExists();
    }

    public void registerUser(Scanner scanner) {
        System.out.println("Register a new user:");
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userService.insertUser(userName, email, password);
        System.out.println("Registration successful! Welcome to Nest Fit!");
    }

    public boolean loginUser(Scanner scanner) {
        System.out.println("Log in:");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : userService.getAllUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful! Welcome back, " + user.getUserName());
                return true;
            }
        }

        System.out.println("Invalid email or password. Try again.");
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserService getUserService() {
        return userService;
    }
}
