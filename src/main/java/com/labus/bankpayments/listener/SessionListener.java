package com.labus.bankpayments.listener;

import com.labus.bankpayments.dao.PendingApDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static Logger logger=Logger.getLogger(String.valueOf(PendingApDao.class));
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("sessionCreated id="+httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("sessionDestroyed");
    }
}
