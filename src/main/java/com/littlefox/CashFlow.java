package com.littlefox;

public class CashFlow {

    private String time;
    private double money;

    public CashFlow(double money,String time){
        this.money=money;
        this.time=time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
