package com.universalstock.repository.api.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.universalstock.exception.ApiErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

public class AccessTokenRequest extends AbstractApiRequest {
    Logger logger = LoggerFactory.getLogger(AccessTokenRequest.class);
    String path = "/oauth2/tokenP";


    public void getAccessToken() throws ApiErrorException {
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("appkey", apiConfig.getKey());
        body.put("appsecret", apiConfig.getSecret());

        logger.debug(body.toString());
        JsonNode jsonNode = resultByPost(path, null, null, body);
        String newToken = jsonNode.get("access_token").asText();
        String newTokenType = jsonNode.get("token_type").asText();
        apiConfig.updateToken(newToken, newTokenType);

        logger.debug("Access token issued successfully: {}", newToken + " " + newTokenType);
    }
}
