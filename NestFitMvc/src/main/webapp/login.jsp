<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String language = request.getParameter("lang");
    if (language == null) {
        language = "en";
    }
    Locale locale = new Locale(language);
    ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= bundle.getString("login.title") %></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        .language-select-container {
            display: flex;
            justify-content: flex-end;
            margin-top: -30px;
        }
        .form-select {
            width: 150px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-container">
            <!-- Language Selection Form -->
            <div class="language-select-container">
                <form method="GET" class="mt-3">
                    <label for="lang" class="me-2"><%= bundle.getString("language.label") %></label>
                    <select name="lang" id="lang" class="form-select" onchange="this.form.submit()">
                        <option value="en" <%= "en".equals(language) ? "selected" : "" %>>English</option>
                        <option value="fr" <%= "fr".equals(language) ? "selected" : "" %>>Français</option>
                        <option value="kiny" <%= "kiny".equals(language) ? "selected" : "" %>>Kinyarwanda</option>
                    </select>
                </form>
            </div>

            <!-- Login Form -->
            <form action="${pageContext.request.contextPath}/login" method="post" class="login-form">
                <div class="mb-3">
                    <label for="email" class="form-label"><%= bundle.getString("login.username") %></label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label"><%= bundle.getString("login.password") %></label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>

                <button type="submit" class="btn btn-primary w-100"><%= bundle.getString("login.button") %></button>
            </form>

            <div class="register-link text-center mt-3">
                <p>Don't have an account? <a href="${pageContext.request.contextPath}/register.jsp">Register</a></p>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>