<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("userEmail") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>NestFit - Edit Post</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="dashboard-container">
        <header class="dashboard-header">
            <h1>Edit Post</h1>
            <div class="user-controls">
                <span class="user-email">${sessionScope.userEmail}</span>
                <form action="${pageContext.request.contextPath}/logout" method="post" class="logout-form">
                    <button type="submit" class="logout-btn">Logout</button>
                </form>
            </div>
        </header>
        
        <main class="dashboard-content">
            <section class="dashboard-section">
                <div class="edit-post-container">
                    <c:if test="${post != null}">
                        <form action="${pageContext.request.contextPath}/posts/update" method="post" class="edit-post-form">
                            <input type="hidden" name="id" value="${post.id}">
                            <div class="form-group">
                                <label for="title">Title:</label>
                                <input type="text" id="title" name="title" value="${post.title}" required class="form-input">
                            </div>
                            <div class="form-group">
                                <label for="content">Content:</label>
                                <textarea id="content" name="content" required class="form-input">${post.content}</textarea>
                            </div>
                            <div class="button-group">
                                <button type="submit" class="update-btn">Update Post</button>
                                <a href="${pageContext.request.contextPath}/dashboard.jsp" class="cancel-btn">Cancel</a>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${post == null}">
                        <div class="error-message">
                            <p>Post not found or invalid post ID.</p>
                            <a href="${pageContext.request.contextPath}/dashboard.jsp" class="back-btn">Back to Dashboard</a>
                        </div>
                    </c:if>
                </div>
            </section>
        </main>
    </div>

    <style>
        .edit-post-container {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-top: 20px;
        }

        .edit-post-form {
            max-width: 800px;
            margin: 0 auto;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-top: 5px;
        }

        textarea.form-input {
            min-height: 150px;
            resize: vertical;
        }

        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }

        .update-btn {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .update-btn:hover {
            background-color: #218838;
        }

        .cancel-btn,
        .back-btn {
            padding: 10px 20px;
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
            cursor: pointer;
        }

        .cancel-btn:hover,
        .back-btn:hover {
            background-color: #5a6268;
        }

        .error-message {
            text-align: center;
            padding: 20px;
        }

        .error-message p {
            color: #dc3545;
            margin-bottom: 20px;
            font-size: 18px;
        }
    </style>
</body>
</html>