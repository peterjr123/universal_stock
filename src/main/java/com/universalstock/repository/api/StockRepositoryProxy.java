package com.universalstock.repository.api;

import com.universalstock.domain.StockPriceHistory;
import com.universalstock.exception.ApiErrorException;
import com.universalstock.repository.api.config.ApiConfig;
import com.universalstock.repository.api.request.AccessTokenRequest;
import com.universalstock.repository.api.request.StockPriceHistoryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockRepositoryProxy {
    ApiConfig apiConfig;
    Logger logger = LoggerFactory.getLogger(StockRepositoryProxy.class);
    private StockPriceHistoryRequest stockPriceHistoryRequest = new StockPriceHistoryRequest("000660", "20200411","20220509", StockPriceHistoryRequest.historyPeriod.Month);
    private AccessTokenRequest accessTokenRequest = new AccessTokenRequest();


    @Autowired
    public void setApiConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        stockPriceHistoryRequest.setApiConfiguration(apiConfig);
        accessTokenRequest.setApiConfiguration(apiConfig);
    }

    public void getApprovalKey() {
//        Map<String, Object> body = new HashMap<>();
//        body.put("grant_type", "client_credentials");
//        body.put("appkey", API_KEY);
//        body.put("secretkey", API_SECRET);
//        ResponseEntity<Map> result = restClient.post()
//                .uri(API_URL + "/oauth2/Approval")
//                .body(body)
//                .retrieve()
//                .toEntity(Map.class);
//
//        Map<String, Object> jsonData = result.getBody();
//        API_APPROVAL_KEY = (String) jsonData.get("approval_key");
    }

    public void getAccessToken() {
        try {
            accessTokenRequest.getAccessToken();
        } catch (ApiErrorException e) {
            logger.error("error code: {}, error msg: {}", e.getErrorCode(), e.getErrorMessage());
            throw new RuntimeException(e);
        }
    }

    public StockPriceHistory getStockPriceHistory() {
        try {
            return stockPriceHistoryRequest.getStockPriceHistory();
        }
        catch (ApiErrorException e) {
            getAccessToken();
            try {
                return stockPriceHistoryRequest.getStockPriceHistory();
            } catch (ApiErrorException ex) {
                e.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void getStocks() {
    }
}
