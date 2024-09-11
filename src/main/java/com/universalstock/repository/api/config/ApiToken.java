package com.universalstock.repository.api.config;

public class ApiToken {
    private String token;
    private String tokenType;

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }
    public String getTokenType() {
        return tokenType;
    }
}
