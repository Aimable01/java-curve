package org.example;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/addNumbers")
public class AddNumbersServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));
        int sum = num1  + num2;

//        PrintWriter out = res.getWriter();
//        out.print("Sum: " + sum);

        //------ forwarding a request to another servlet
//        req.setAttribute("total", sum);
//        RequestDispatcher rd = req.getRequestDispatcher("answer");
//        rd.forward(req,res);

        //---- redirecting a response
//        res.sendRedirect("http://localhost:8080/Servlets_Web_exploded/redirect?sum="+sum);

        //----- using sessions now
//        HttpSession session = req.getSession();
//        session.setAttribute("sum", sum);
//        res.sendRedirect("redirect");

        //---- using cookies
        Cookie sumCok = new Cookie("sum", String.valueOf(sum));
        res.addCookie(sumCok);
        res.sendRedirect("redirect");
    }
}