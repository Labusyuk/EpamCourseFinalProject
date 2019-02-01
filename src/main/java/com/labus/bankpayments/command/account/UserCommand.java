package com.labus.bankpayments.command.account;

import com.labus.bankpayments.command.Command;
import com.labus.bankpayments.dao.AccountDao;
import com.labus.bankpayments.dao.CrediteDao;
import com.labus.bankpayments.dao.DepositeDao;
import com.labus.bankpayments.dao.PaymentDao;
import com.labus.bankpayments.entity.*;
import com.labus.bankpayments.exception.CommandException;
import com.labus.bankpayments.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        Iterator<Account> accountIter = accounts.iterator();
        while(accountIter.hasNext()){
            Account account = accountIter.next();
            if(account.getType()==1){
                req.getSession().setAttribute("crediteExist", "true");
                break;
            }
        }
        req.getSession().setAttribute("accounts", accounts);
        numberOfAccount = req.getParameter("numberOfAccount");
        if(numberOfAccount!=null){
            accountIter = accounts.iterator();
            while (accountIter.hasNext()){
                Account account = accountIter.next();
                if(account.getNumber()==Long.parseLong(numberOfAccount)) {
                    req.getSession().setAttribute("selAccount", account);
                    req.getSession().setAttribute("selNumberAccount",numberOfAccount);
                    try {
                    switch (account.getType()){
                        case 1:
                                Credite credite = new CrediteDao().getByNumber(account.getNumber());
                                req.setAttribute("credite",credite);
                                 break;
                        case 2:
                                Deposite deposite = new DepositeDao().getByNumber(account.getNumber());
                                req.setAttribute("deposite",deposite);

                                break;
                    }
                        List<Payment> payments = new PaymentDao().getByNumber(account.getNumber());
                    req.setAttribute("payments",payments);
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                }
            }

        };///
       return "user";
    }
}
