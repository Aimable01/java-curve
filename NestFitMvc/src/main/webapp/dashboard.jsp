<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
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
                <h2>Your Dashboard</h2>
                <div class="dashboard-info">
                    <p>Welcome back! This is your personal fitness dashboard.</p>
                    <!-- Add more dashboard content here -->
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
    </style>
</body>
</html>