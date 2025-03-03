<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Register - NestFit</title>
    <link rel="stylesheet" type="text/css" href="css/auth.css" />
  </head>
  <body>
    <div class="auth-container">
      <h2>Create NestFit Account</h2>
      <% if (request.getAttribute("error") != null) { %>
      <div class="error-message">${error}</div>
      <% } %>
      <form action="register" method="post">
        <div class="form-group">
          <label for="name">Full Name</label>
          <input type="text" id="name" name="name" required />
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" name="email" required />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" id="password" name="password" required />
        </div>
        <div class="form-group">
          <label for="confirmPassword">Confirm Password</label>
          <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            required
          />
        </div>
        <button type="submit" class="btn-submit">Register</button>
      </form>
      <div class="auth-link">
        <p>Already have an account? <a href="login.jsp">Login here</a></p>
      </div>
    </div>
  </body>
</html>
