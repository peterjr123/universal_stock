package com.universalstock.domain;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StockPriceHistoryTest {
    Logger logger = LoggerFactory.getLogger(StockPriceHistoryTest.class);
    @Test
    public void orderingTest() {
        StockPriceHistory stockPriceHistory = new StockPriceHistory();
        stockPriceHistory.addPrice("20220206", "111");
        stockPriceHistory.addPrice("20240206", "222");
        stockPriceHistory.addPrice("20210206", "333");

        List<String> dates = stockPriceHistory.getDateAscending();
        for (String date : dates) {
            System.out.println(date + " " + stockPriceHistory.getPrice(date));
        }
    }

    @Test
    public void logTest() {
        logger.debug("Hello world!");
    }
}
