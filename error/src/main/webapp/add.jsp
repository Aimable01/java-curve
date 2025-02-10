<%--
  Created by IntelliJ IDEA.
  User: Aimable
  Date: 10/02/2025
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="errorPage.jsp" %>
<html>
<head>
    <title>Add Numbers</title>
</head>
<body>
<%
    String num1Str = request.getParameter("num1");
    String num2Str = request.getParameter("num2");

    try {
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);
        int sum = num1 + num2;

        out.print("Sum is: " + sum);
    } catch (NumberFormatException e) {
        throw new ServletException("Invalid input: Please provide valid integers for num1 and num2.", e);
    } catch (NullPointerException e) {
        throw new ServletException("Missing input: Please provide both num1 and num2.", e);
    }
%>
</body>
</html>