package com.labus.bankpayments.entity;

import java.util.Date;

public class PendingAp extends Entity {
    private String login;
    private Date date;

    public PendingAp() {
    }

    public PendingAp(int id, String login, Date date) {
        super(id);
        this.login = login;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
