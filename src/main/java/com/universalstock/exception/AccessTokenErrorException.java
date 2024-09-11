package com.universalstock.exception;

public class AccessTokenErrorException extends ApiErrorException {
    public AccessTokenErrorException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
