package org.aimable.nestfitmvc.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aimable.nestfitmvc.ServiceImpl.UserServiceImpl;
import org.aimable.nestfitmvc.model.User;
import org.aimable.nestfitmvc.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login", "/register"})
public class AuthController extends HttpServlet {
    private UserService userService;

    public void init() throws ServletException {
        try {
            userService = new UserServiceImpl();
        } catch (SQLException e) {
            String errorMessage = "Failed to initialize UserService: " + e.getMessage();
            e.printStackTrace();
            throw new ServletException(errorMessage, e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        
        try {
            if ("/register".equals(path)) {
                handleRegistration(request, response);
            } else if ("/login".equals(path)) {
                handleLogin(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred. Please try again.");
            request.getRequestDispatcher(path + ".jsp").forward(request, response);
        }
    }

    private void handleRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        try {
            userService.registerUser(user);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            userService.login(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("dashboard.jsp");
        } catch (SQLException e) {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        request.getRequestDispatcher(path + ".jsp").forward(request, response);
    }
}