package com.universalstock.domain;

public class StockPrice {
    String time;
    int price;

    StockPrice(String time, int price) {
        this.time = time;
        this.price = price;
    }
    public StockPrice() {

    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
