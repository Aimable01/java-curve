<%--
  Created by IntelliJ IDEA.
  User: Aimable
  Date: 04/02/2025
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
</head>
<body>
<%
    String num1Str = request.getParameter("num1");
    String num2Str = request.getParameter("num2");

    int num1 = Integer.parseInt(num1Str);
    int num2 = Integer.parseInt(num2Str);
    int sum = num1 + num2;

    out.print("sum is: " + sum);
%>
</body>
</html>