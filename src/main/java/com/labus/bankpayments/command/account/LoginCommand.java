package com.labus.bankpayments.command.account;

import com.labus.bankpayments.command.Command;
import com.labus.bankpayments.dao.UserDao;
import com.labus.bankpayments.entity.User;
import com.labus.bankpayments.exception.CommandException;
import com.labus.bankpayments.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
       if(login == null || password == null)
            return "login";
        User user = new UserDao().getByLogAndPass(login, String.valueOf(password.hashCode()));
        if (user == null ) {
            req.getSession().setAttribute("message", "login_error");
            return "default";
        }
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("role", user.getRole());
        return "user";
    }
}
