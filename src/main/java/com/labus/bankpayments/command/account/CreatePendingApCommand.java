package com.labus.bankpayments.command.account;

import com.labus.bankpayments.command.Command;
import com.labus.bankpayments.dao.CrediteDao;
import com.labus.bankpayments.dao.PendingApDao;
import com.labus.bankpayments.entity.PendingAp;
import com.labus.bankpayments.entity.User;
import com.labus.bankpayments.exception.CommandException;
import com.labus.bankpayments.exception.DaoException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

public class CreatePendingApCommand implements Command{
    private static Logger logger=Logger.getLogger(String.valueOf(CrediteDao.class));
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        if(req.getSession().getAttribute("crediteExist")!=null)return "user";
        String login = ((User)(req.getSession().getAttribute("user"))).getLogin();
        PendingAp pendingAp = new PendingAp(0,login,new Timestamp(new Date().getTime()));
        try {
            new PendingApDao().create(pendingAp);
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
        return "user";
    }
}
