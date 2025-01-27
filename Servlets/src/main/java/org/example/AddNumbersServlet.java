package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addNumbers")
public class AddNumbersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // get the two numbers
        String numStr1 = request.getParameter("num1");
        String numStr2 = request.getParameter("num2");

        try {
            int num1 = Integer.parseInt(numStr1);
            int num2 = Integer.parseInt(numStr2);
            int sum = num1 + num2;

            // Prepare response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Result: " + sum + "</h2>");
            out.println("<a href='index.html'>Go Back</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            response.getWriter().println("<h3>Invalid input. Please enter valid numbers.</h3>");
        }
    }
}
