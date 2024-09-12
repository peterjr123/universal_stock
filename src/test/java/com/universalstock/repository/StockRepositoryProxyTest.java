package com.universalstock.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.universalstock.domain.StockPriceHistory;
import com.universalstock.repository.api.StockRepositoryProxy;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class StockRepositoryProxyTest {
    @Test
    public void getStockPriceHistory() throws JsonProcessingException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/applicationContext.xml");
//
//        StockRepositoryProxy proxy = context.getBean(StockRepositoryProxy.class);
//        StockPriceHistory history = proxy.getStockPriceHistory();
//        List<String> dates = history.getDateAscending();
//        for(String date : dates) {
//            System.out.println("date: " + date + " price: " + history.getPrice(date));
//        }
    }
}
