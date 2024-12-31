package com.laboratoire.laboratoire_service.controller;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private int code;
    private String message;

    private HttpStatus status;

    public ApiResponse(int i, String s) {
        this.code = i;
        this.message = s ;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
