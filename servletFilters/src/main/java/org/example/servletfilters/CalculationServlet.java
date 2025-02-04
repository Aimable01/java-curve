package org.example.servletfilters;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calculationServlet", value = "/sum")
public class CalculationServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int num1 = Integer.parseInt(req.getParameter("num1"));
            int num2 = Integer.parseInt(req.getParameter("num2"));
            int sum = num1 + num2;

            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<p>Sum: " + sum + "</p>");
        } catch (NumberFormatException e) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<p>Invalid input. Please provide valid integers for num1 and num2.</p>");
        }
    }
}