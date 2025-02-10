<%@ page import="com.beans.StudentBean" %><%--
  Created by IntelliJ IDEA.
  User: Aimable
  Date: 10/02/2025
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int studentId = Integer.parseInt(request.getParameter("studentId"));
    String studentFirstName  = request.getParameter("studentFirstName");
    String studentLastName  = request.getParameter("studentLastName");

    StudentBean st = new StudentBean();
    st.setStudentId(studentId);
    st.setFirstName(studentFirstName);
    st.setLastName(studentLastName);

    session.setAttribute("student", st);
%>
<jsp:forward page="display.jsp"></jsp:forward>
</body>
</html>
