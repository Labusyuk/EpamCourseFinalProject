package com.labus.bankpayments.entity;

public class Credite extends Entity {
    private long account_number;
    private long limit;
    private long debt;
    private long amount_interest;
    private short rate;

    public Credite() {
    }

    public Credite(int id, long account_number, long limit, long debt, long amount_interest, short rate) {
        super(id);
        this.account_number = account_number;
        this.limit = limit;
        this.debt = debt;
        this.amount_interest = amount_interest;
        this.rate = rate;
    }

    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getDebt() {
        return debt;
    }

    public void setDebt(long debt) {
        this.debt = debt;
    }

    public long getAmount_interest() {
        return amount_interest;
    }

    public void setAmount_interest(long amount_interest) {
        this.amount_interest = amount_interest;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(short rate) {
        this.rate = rate;
    }
}
