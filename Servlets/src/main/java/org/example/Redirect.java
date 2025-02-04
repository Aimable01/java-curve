package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class Redirect extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();

        // -- get data from parameters
//        int sum = Integer.parseInt(req.getParameter("sum"));
//        out.print("After the redirect sum is: " + sum);

        //-- get data from the session
//        HttpSession session = req.getSession();
//        int sum = (int)session.getAttribute("sum");
//        session.removeAttribute("sum"); // to remove the attribute
//        session.invalidate(); // to logout
//        out.print("After getting data from session: "+sum);

        //--- get data from the cookie
        Cookie cookies[] = req.getCookies();
        int sum = Integer.parseInt(cookies[0].getValue());
        out.print("Data from cookies: " + sum);
    }
}
