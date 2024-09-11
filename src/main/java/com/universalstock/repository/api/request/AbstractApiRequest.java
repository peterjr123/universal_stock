package com.universalstock.repository.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.universalstock.exception.ApiErrorException;
import com.universalstock.repository.api.config.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Map;

public class AbstractApiRequest {
    protected ApiConfig apiConfig;
    protected RestClient restClient;
    Logger logger = LoggerFactory.getLogger(AbstractApiRequest.class);

    public void setApiConfiguration(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public void restClientSetup() {
        restClient = RestClient.builder()
                .baseUrl(apiConfig.getUrl())
                .defaultHeader("content-type", "application/json; utf-8")
                .build();
    }

    public JsonNode resultByGet(String path, MultiValueMap<String, String> params, HttpHeaders headers) throws ApiErrorException {
        if(restClient == null) {
            restClientSetup();
        }

        logger.debug("get request to: {}", apiConfig.getUrl() + path);
        logger.debug("get params: {}", params);
        logger.debug("get headers: {}", headers);


        String result = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParams(params)
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(HttpStatusCode::isError, ((request, response) -> {
                    logger.debug("error on api get request. {} {}", response.getStatusCode(), response.getStatusText());
                }))
                .body(String.class);

        return stringToJson(result);
    }

    public JsonNode resultByPost(String path, MultiValueMap<String, String> params, HttpHeaders headers, Map<String, String> body) throws ApiErrorException {
        if(restClient == null) {
            restClientSetup();
        }
        logger.debug("post request to: {}", apiConfig.getUrl() + path);
        logger.debug("post params: {}", params);
        logger.debug("post headers: {}", headers);
        logger.debug("post body: {}", body);

        ResponseEntity<String> result = restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParams(params)
                        .build())
                .headers(httpHeaders -> {
                    if(headers != null) {
                        httpHeaders.addAll(headers);
                    }
                })
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, ((request, response) -> {
                    logger.debug("error on api post request. {} {}", response.getStatusCode(), response.getStatusText());
                }))
                .toEntity(String.class);

        logger.debug(result.getBody());
        return stringToJson(result.getBody().toString());
    }

    public JsonNode stringToJson(String jsonString) throws ApiErrorException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;

        try {
            jsonNode = objectMapper.readTree(jsonString);
            if(jsonNode.has("rt_cd") && !jsonNode.get("rt_cd").asText().equals("0")) {
                throw new ApiErrorException(jsonNode.get("msg_cd").asText(), jsonNode.get("msg1").asText());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonNode;
    }
}
