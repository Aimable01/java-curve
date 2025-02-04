package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/answer")
public class TotalServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int tot = (int)req.getAttribute("total");
        PrintWriter out = res.getWriter();
        out.print("Sum is changed to: "+tot+"\n");
    }
}
