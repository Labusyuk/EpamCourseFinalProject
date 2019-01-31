package com.labus.bankpayments.controller;

import com.labus.bankpayments.command.Command;
import com.labus.bankpayments.command.CommandFactory;
import com.labus.bankpayments.command.account.LoginCommand;
import com.labus.bankpayments.db.ConnectionPool;
import com.labus.bankpayments.exception.CommandException;
import org.apache.log4j.Logger;

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
        System.out.println(request.getPathInfo());
        try {
            result = command.execute(request, response);
            System.out.println(request.getMethod()+"   "+result);
        } catch (CommandException e) {
            throw new ServletException();
        }
        if(result==null){
            return;
        }
        if (request.getMethod().equals("POST")) {
            response.sendRedirect(result);
        } else if (request.getMethod().equals("GET")){
            request.getRequestDispatcher("/jsp/" + result + ".jsp").forward(request,response);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().dispose();
    }
}
