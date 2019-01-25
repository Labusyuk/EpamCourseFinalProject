package com.labus.bankpayments.entity;

import java.util.Date;

public class Payment extends Entity {
private long account_number;
private long to;
private byte actions;
private long amount;
private String description;
private Date date;

    public Payment() {
    }

    public Payment(int id, long account_number, byte actions, long amount, String description, Date date) {
        super(id);
        this.account_number = account_number;
        this.to = to;
        this.actions = actions;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public byte getActions() {
        return actions;
    }

    public void setActions(byte actions) {
        this.actions = actions;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
