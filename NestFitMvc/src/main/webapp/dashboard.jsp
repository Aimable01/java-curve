<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("userEmail") == null) {
        response.sendRedirect("login.jsp"); // Redirect to login page
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>NestFit - Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="dashboard-container">
        <header class="dashboard-header">
            <h1>Welcome to NestFit</h1>
            <div class="user-controls">
                <span class="user-email">${sessionScope.userEmail}</span>
                <form action="${pageContext.request.contextPath}/logout" method="post" class="logout-form">
                    <button type="submit" class="logout-btn">Logout</button>
                </form>
            </div>
        </header>
        
        <main class="dashboard-content">
            <section class="dashboard-section">
                <h2>Your Posts</h2>
                <div class="posts-container">
                    <div class="create-post-section">
                        <h3>Create New Post</h3>
                        <form action="${pageContext.request.contextPath}/posts/create" method="post" class="create-post-form">
                            <div class="form-group">
                                <label for="title">Title:</label>
                                <input type="text" id="title" name="title" required class="form-input">
                            </div>
                            <div class="form-group">
                                <label for="content">Content:</label>
                                <textarea id="content" name="content" required class="form-input"></textarea>
                            </div>
                            <button type="submit" class="create-btn">Create Post</button>
                        </form>
                    </div>
                    
                    <div class="posts-list">
                        <h3>Your Posts</h3>
                        <table class="posts-table">
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Content</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.posts}" var="post">
                                    <tr>
                                        <td>${post.title}</td>
                                        <td>${post.content}</td>
                                        <td class="action-buttons">
                                            <form action="${pageContext.request.contextPath}/posts/edit" method="get" style="display: inline;">
                                                <input type="hidden" name="id" value="${post.id}">
                                                <button type="submit" class="edit-btn">Edit</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/posts/delete" method="post" style="display: inline;">
                                                <input type="hidden" name="id" value="${post.id}">
                                                <button type="submit" class="delete-btn">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </main>
    </div>

    <style>
        .dashboard-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .dashboard-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .user-controls {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .user-email {
            font-weight: bold;
        }

        .logout-btn {
            padding: 8px 16px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .logout-btn:hover {
            background-color: #c82333;
        }

        .dashboard-content {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .dashboard-section {
            margin-bottom: 30px;
        }

        .dashboard-section h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .dashboard-info {
            line-height: 1.6;
        }
        .posts-container {
            margin-top: 20px;
        }

        .create-post-section {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
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
            min-height: 100px;
            resize: vertical;
        }

        .create-btn {
            background-color: #28a745;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .create-btn:hover {
            background-color: #218838;
        }

        .posts-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .posts-table th,
        .posts-table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .posts-table th {
            background-color: #f8f9fa;
            font-weight: bold;
        }

        .action-buttons {
            white-space: nowrap;
        }

        .edit-btn,
        .delete-btn {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
        }

        .edit-btn {
            background-color: #ffc107;
            color: #000;
        }

        .edit-btn:hover {
            background-color: #e0a800;
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
        }

        .delete-btn:hover {
            background-color: #c82333;
        }
    </style>
</body>
</html>