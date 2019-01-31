package com.labus.bankpayments.entity;

import java.util.Date;

public class Account extends Entity {
    private long number;
    private short type;
    private String owner;
    private long balance;
    private Date validity;

    public Account() {
    }

    public Account(int id, long number, short type, String owner, long balance, Date validity) {
        super(id);
        this.number = number;
        this.type = type;
        this.owner = owner;
        this.balance = balance;
        this.validity = validity;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public void updateBalance(Long amount){
        balance+=amount;
    }
}
