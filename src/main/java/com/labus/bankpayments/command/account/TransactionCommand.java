package com.labus.bankpayments.command.account;

import com.labus.bankpayments.command.Command;
import com.labus.bankpayments.dao.AccountDao;
import com.labus.bankpayments.dao.PaymentDao;
import com.labus.bankpayments.entity.Account;
import com.labus.bankpayments.entity.Payment;
import com.labus.bankpayments.exception.CommandException;
import com.labus.bankpayments.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

public class TransactionCommand implements Command{
    private Account accountTo, accountFrom;
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String amount = req.getParameter("amount");
        String number = req.getParameter("account_number");
        String numberOfAccount = (String)req.getSession().getAttribute("selNumberAccount");
        if(amount==null || number==null || numberOfAccount==null)
        return "user";
        try {
            accountFrom = new AccountDao().getByNumber(Long.parseLong(numberOfAccount));
            accountTo = new AccountDao().getByNumber(Long.parseLong(number));
        accountFrom.updateBalance(-Long.parseLong(amount));
        accountTo.updateBalance(Long.parseLong(amount));
        new AccountDao().update(accountFrom);
        new AccountDao().update(accountTo);
        Payment payment = new Payment(0,Long.parseLong(numberOfAccount),Long.parseLong(number), (byte) 1,Long.parseLong(amount),null,new Timestamp(new Date().getTime()));
        new PaymentDao().create(payment);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return "user";
    }
}
