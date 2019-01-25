package com.labus.bankpayments.entity;

public class Deposite extends Entity {
    private long account_number;
    private short rate;

    public Deposite() {
    }

    public Deposite(int id, long account_number, byte rate) {
        super(id);
        this.account_number = account_number;
        this.rate = rate;
    }

    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }

    public short getRate() {
        return rate;
    }

    public void setRate(short rate) {
        this.rate = rate;
    }
}
