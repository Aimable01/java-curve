package org.example.servletfilters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/sum")
public class CalculationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        int num2 = Integer.parseInt(request.getParameter("num2"));

        PrintWriter out = servletResponse.getWriter();
        if (num2 < 0) {
            out.println("num 2 cannot be negative");
        }else{
            filterChain.doFilter(request, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}