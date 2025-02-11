<%@ page import="com.beans.StudentBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
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
    /**
    int studentId = Integer.parseInt(request.getParameter("studentId"));
    String studentFirstName  = request.getParameter("studentFirstName");
    String studentLastName  = request.getParameter("studentLastName");

    StudentBean st = new StudentBean();
    st.setStudentId(studentId);
    st.setFirstName(studentFirstName);
    st.setLastName(studentLastName);

    session.setAttribute("student", st);


    //-- other expressions usage
    String firstName = request.getParameter("studentFirstName");
    request.setAttribute("fname", firstName);
    */

   // Create StudentBean objects
    StudentBean st1 = new StudentBean();
    st1.setStudentId(1);
    st1.setFirstName("Shema");
    st1.setLastName("Paul");

    StudentBean st2 = new StudentBean();
    st2.setStudentId(2);
    st2.setFirstName("Alice");
    st2.setLastName("Johnson");

    StudentBean st3 = new StudentBean();
    st3.setStudentId(3);
    st3.setFirstName("Bob");
    st3.setLastName("Smith");

    // Add students to a list
    List<StudentBean> students = new ArrayList<>();
    students.add(st1);
    students.add(st2);
    students.add(st3);

    // Set the list as a request attribute
    request.setAttribute("students", students);

    //-- the second one
    Map<String, String> people = new HashMap<>();
    people.put("Gabby","078243183");
    people.put("Aimable","078243183");
    people.put("Goal","078243183");

    request.setAttribute("people", people);

%>
<%--First name: ${param.studentFirstName}--%>
<%--<jsp:forward page="display.jsp"></jsp:forward>--%>

Aimable: ${people.Aimable}

<table border="1">
    <tr>
        <th>Student ID</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    <%
        for (StudentBean student : students) {
    %>
    <tr>
        <td><%= student.getStudentId() %></td>
        <td><%= student.getFirstName() %></td>
        <td><%= student.getLastName() %></td>
    </tr>
    <%
        }
    %>
</table>
</ul>
</body>
</html>
