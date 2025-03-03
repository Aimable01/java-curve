<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - NestFit</title>
    <link rel="stylesheet" type="text/css" href="css/auth.css">
</head>
<body>
    <div class="auth-container">
        <h2>Login to NestFit</h2>
        <% if (request.getAttribute("error") != null) { %>
            <div class="error-message">${error}</div>
        <% } %>
        <form action="login" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn-submit">Login</button>
        </form>
        <div class="auth-link">
            <p>Don't have an account? <a href="register">Register here</a></p>
        </div>
    </div>
</body>
</html>