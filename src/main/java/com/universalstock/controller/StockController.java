package com.universalstock.controller;

import com.universalstock.domain.Stock;
import com.universalstock.domain.StockPrice;
import com.universalstock.domain.StockPriceHistory;
import com.universalstock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StockController {
    Logger logger = LoggerFactory.getLogger(StockController.class);
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping("/stock/price")
    @ResponseBody
    public ResponseEntity<List<StockPrice>> getStockPriceHistory() {
        logger.debug("request path: /stock/price");
        return new ResponseEntity<>(stockService.getStockPriceHistory(), HttpStatus.OK);
    }
}
