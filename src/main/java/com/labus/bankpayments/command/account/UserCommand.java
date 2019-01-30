package com.labus.bankpayments.command.account;

import com.labus.bankpayments.command.Command;
import com.labus.bankpayments.dao.AccountDao;
import com.labus.bankpayments.entity.Account;
import com.labus.bankpayments.entity.User;
import com.labus.bankpayments.exception.CommandException;
import com.labus.bankpayments.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class UserCommand implements Command {
    List<Account> accounts;
    String numberOfAccount;
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            accounts = new AccountDao().getByOwner(((User)(req.getSession().getAttribute("user"))).getLogin());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("accounts", accounts);
        numberOfAccount = req.getParameter("numberOfAccount");
        System.out.println(numberOfAccount);
        if(numberOfAccount!=null){
            Iterator<Account> accountIter = accounts.iterator();
            while (accountIter.hasNext()){
                Account account = accountIter.next();
                if(account.getNumber()==Long.parseLong(numberOfAccount)) {
                    req.setAttribute("selAccount", account);
                    System.out.println("ok");
                }
            }
        };///
       return "user";
    }
}
