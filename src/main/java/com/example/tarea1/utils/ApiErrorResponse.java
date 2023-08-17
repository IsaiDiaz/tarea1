package com.example.tarea1.utils;
import org.springframework.http.HttpStatus;

public class ApiErrorResponse {
    private HttpStatus status;
    private String message;

    public ApiErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
