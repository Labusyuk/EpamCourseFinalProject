package com.labus.bankpayments.controller;

import com.labus.bankpayments.command.Command;
import com.labus.bankpayments.command.CommandFactory;
import com.labus.bankpayments.db.ConnectionPool;
import com.labus.bankpayments.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProcessRequest(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProcessRequest(request,response);
    }
    private void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(request);
        String result;
        try {
            result = command.execute(request, response);
        } catch (CommandException e) {
            throw new ServletException();
        }
        if (request.getMethod().equals("POST")) {
        } else if (request.getMethod().equals("GET")){
            System.out.println(request.getPathInfo());
            request.getRequestDispatcher("/jsp/"+result+".jsp").forward(request,response);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().dispose();
    }
}
