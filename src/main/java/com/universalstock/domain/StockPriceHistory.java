package com.universalstock.domain;

import java.util.*;

public class StockPriceHistory {
    Map<String, String> datePriceMapping = new HashMap<>();

    public void addPrice(String date, String price) {
        datePriceMapping.put(date, price);
    }
    public String getPrice(String date) {
        return datePriceMapping.get(date);
    }
    public List<String> getDateAscending() {
        List<String> keySet = new ArrayList<>(datePriceMapping.keySet());
        keySet.sort((o1, o2) -> {
            int date1 = Integer.parseInt(o1);
            int date2 = Integer.parseInt(o2);
            if (date1 > date2)
                return 1;
            else if (date1 < date2)
                return -1;
            return 0;
        });
        return keySet;
    }
    public List<StockPrice> getStockPriceHistory() {
        List<StockPrice> stockPriceHistory = new ArrayList<>();
        List<String> dateAscending = getDateAscending();
        for (String date : dateAscending) {
            StockPrice stockPrice = new StockPrice(date, Integer.parseInt(datePriceMapping.get(date)));
            stockPriceHistory.add(stockPrice);
        }
        return stockPriceHistory;
    }
}
