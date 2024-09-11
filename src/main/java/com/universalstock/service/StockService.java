package com.universalstock.service;

import com.universalstock.domain.Stock;
import com.universalstock.domain.StockPrice;
import com.universalstock.domain.StockPriceHistory;
import com.universalstock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    private StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getStockList() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock("samsung", 100000));
        return stockList;
    }

    public List<StockPrice> getStockPriceHistory() {
        StockPriceHistory stockPriceHistory = stockRepository.getStockPriceHistory();
        return stockPriceHistory.getStockPriceHistory();
    }
}
