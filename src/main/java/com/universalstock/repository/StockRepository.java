package com.universalstock.repository;

import com.universalstock.domain.StockPriceHistory;
import com.universalstock.repository.api.StockRepositoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepository {
    StockRepositoryProxy stockRepositoryProxy;

    @Autowired
    public StockRepository(StockRepositoryProxy stockRepositoryProxy) {
        this.stockRepositoryProxy = stockRepositoryProxy;
    }

    public StockPriceHistory getStockPriceHistory() {
        return stockRepositoryProxy.getStockPriceHistory();
    }
}
