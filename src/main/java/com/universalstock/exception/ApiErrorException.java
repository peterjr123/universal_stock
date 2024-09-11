package com.universalstock.exception;

public class ApiErrorException extends Exception {
    private String errorCode;
    private String errorMessage;

    public ApiErrorException(String errorCode, String errorMessage) {
        super("code: " + errorCode + ", message: " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
