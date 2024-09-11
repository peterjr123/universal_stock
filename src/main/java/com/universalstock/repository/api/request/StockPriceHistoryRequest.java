package com.universalstock.repository.api.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.universalstock.domain.StockPriceHistory;
import com.universalstock.exception.ApiErrorException;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class StockPriceHistoryRequest extends AbstractApiRequest {
    public enum historyPeriod { Day, Week, Month, Year};
    String FID_COND_MRKT_DIV_CODE = "J";
    String FID_INPUT_ISCD;
    String FID_INPUT_DATE_1;
    String FID_INPUT_DATE_2;
    String FID_PERIOD_DIV_CODE;
    String FID_ORG_ADJ_PRC = "0";


    public StockPriceHistoryRequest(String stockCode, String startDate, String endDate, historyPeriod historyPeriod) {
        this.FID_INPUT_ISCD = stockCode;
        this.FID_INPUT_DATE_1 = startDate;
        this.FID_INPUT_DATE_2 = endDate;
        this.FID_PERIOD_DIV_CODE = historyPeriod.name().substring(0, 1);
    }

    public StockPriceHistory getStockPriceHistory() throws ApiErrorException {
        String path = "/uapi/domestic-stock/v1/quotations/inquire-daily-itemchartprice";

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + apiConfig.getToken());
        headers.add("appkey", apiConfig.getKey());
        headers.add("appsecret", apiConfig.getSecret());
        headers.add("tr_id", "FHKST03010100");
        headers.add("custtype", "P");

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("FID_COND_MRKT_DIV_CODE", FID_COND_MRKT_DIV_CODE);
        queryParams.add("FID_INPUT_ISCD", FID_INPUT_ISCD);
        queryParams.add("FID_INPUT_DATE_1", FID_INPUT_DATE_1);
        queryParams.add("FID_INPUT_DATE_2", FID_INPUT_DATE_2);
        queryParams.add("FID_PERIOD_DIV_CODE", FID_PERIOD_DIV_CODE);
        queryParams.add("FID_ORG_ADJ_PRC", FID_ORG_ADJ_PRC);

        JsonNode jsonNode = resultByGet(path, queryParams, headers);

        JsonNode output2 = jsonNode.get("output2");
        StockPriceHistory stockPriceHistory = new StockPriceHistory();
        for(int i = 0; i < output2.size(); i++) {
            stockPriceHistory.addPrice(output2.get(i).get("stck_bsop_date").asText(), output2.get(i).get("stck_clpr").asText());
        }

        logger.debug("received stock price history: {}", output2);

        return stockPriceHistory;
    }
}
