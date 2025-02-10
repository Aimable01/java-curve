<%--
  Created by IntelliJ IDEA.
  User: Aimable
  Date: 10/02/2025
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="registrationProcess.jsp">
        <table>
            <tr>
                <td>Student ID</td>
                <td><input type="text" name="studentId" /></td>
            </tr>
            <tr>
                <td>Student First Name</td>
                <td><input type="text" name="studentFirstName" /></td>
            </tr>
            <tr>
                <td>Student Last Name</td>
                <td><input type="text" name="studentLastName" /></td>
            </tr>
            <tr>

                <td><input type="submit" value="save" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
