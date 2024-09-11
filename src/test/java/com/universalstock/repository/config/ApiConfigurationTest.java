package com.universalstock.repository.config;

import com.universalstock.repository.api.config.ApiConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApiConfigurationTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/applicationContext.xml");

        ApiConfig config = context.getBean(ApiConfig.class);
        Assertions.assertNotNull(config.getUrl());
        Assertions.assertNotNull(config.getKey());
        Assertions.assertNotNull(config.getSecret());
        Assertions.assertNotNull(config.getToken());
        Assertions.assertNotNull(config.getTokenType());

        System.out.println("url: " + config.getUrl());
        System.out.println("key: " + config.getKey());
        System.out.println("secret: " + config.getSecret());
        System.out.println("token: " + config.getToken());
        System.out.println("token type: " + config.getTokenType());
    }
}
