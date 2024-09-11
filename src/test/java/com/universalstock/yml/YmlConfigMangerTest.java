package com.universalstock.yml;

import com.universalstock.repository.api.config.ApiConfig;
import com.universalstock.repository.api.config.ApiToken;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

public class YmlConfigMangerTest {
    private final String YAML_FILE_PATH = "src/main/resources/application.yml";

    @Test
    public void testLoad() {
        YamlConfigManager manager = new YamlConfigManager();
        ApiToken apiToken = (ApiToken) manager.loadYaml(ApiToken.class, new File(YAML_FILE_PATH));
        System.out.println(apiToken.getToken());
        System.out.println(apiToken.getTokenType());
    }

    @Test
    public void testSave() {
        YamlConfigManager manager = new YamlConfigManager();
        ApiToken apiToken = new ApiToken();
        apiToken.setToken("tk");
        apiToken.setTokenType("tt");
        manager.saveYaml(apiToken.getClass(), apiToken, new File(YAML_FILE_PATH));
    }

    @Test
    public void testClasspath() {
        ClassPathResource classPathResource = new ClassPathResource("/application-secrets.yml");
        System.out.println(classPathResource.exists());
    }

    @Test
    public void testLoadYaml() {
        ApiConfig apiConfig = new ApiConfig();
        System.out.println(apiConfig.getToken());
    }
}
