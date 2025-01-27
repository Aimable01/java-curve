package org.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello, Servlet!</h1>");
    }
}
