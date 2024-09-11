package com.universalstock.repository.api.config;

import com.universalstock.yml.YamlConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-secrets.properties")
})
public class ApiConfig {
    private ClassPathResource classPathResource = new ClassPathResource("/application-secrets.yml");
    private YamlConfigManager yamlConfigManager = new YamlConfigManager();
    Logger logger = LoggerFactory.getLogger(ApiConfig.class);

    @Value("${api.config.url}")
    private String API_URL;

    @Value("${api.secret.key}")
    private String API_KEY;

    @Value("${api.secret.secret}")
    private String API_SECRET;

    private ApiToken apiToken;

    public String getUrl() {
        return API_URL;
    }
    public String getKey() {
        return API_KEY;
    }
    public String getSecret() {
        return API_SECRET;
    }
    public String getToken() {
        if(apiToken == null) {
            loadApiTokenFromYaml();
        }
        return apiToken.getToken();
    }
    public String getTokenType() {
        if(apiToken == null) {
            loadApiTokenFromYaml();
        }
        return apiToken.getTokenType();
    }
    public void updateToken(String token, String tokenType) {
        apiToken.setToken(token);
        apiToken.setTokenType(tokenType);
        saveApiTokenToYaml();
    }

    private void loadApiTokenFromYaml() {
        try {
            apiToken = (ApiToken) yamlConfigManager.loadYaml(ApiToken.class, classPathResource.getFile());
        }
        catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }
    private void saveApiTokenToYaml() {
        try {
            yamlConfigManager.saveYaml(ApiToken.class, apiToken, classPathResource.getFile());
        }
        catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }
}
