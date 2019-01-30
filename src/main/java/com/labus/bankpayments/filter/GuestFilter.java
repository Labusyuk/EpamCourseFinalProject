package com.labus.bankpayments.filter;

import com.labus.bankpayments.command.CommandFactory;
import com.labus.bankpayments.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuestFilter implements Filter {
    private List<String> allowedURLs;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedURLs = new ArrayList<>();
        allowedURLs.add("/login");
        allowedURLs.add("/user");
        allowedURLs.add("/admin");
        allowedURLs.add("/");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getSession().getAttribute("user") == null && !allowedURLs.contains(req.getPathInfo())) {
            req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
            System.out.println("null");
            return;
        }else{
            if(!CommandFactory.commandsMap.containsKey(req.getPathInfo()))
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
