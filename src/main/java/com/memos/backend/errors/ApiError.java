package com.memos.backend.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class ApiError {

    private int status;

    private String message;

    private String path;

    private long timestamp;

    private Map<String, String> validationErrors;

    public ApiError(int status, String message, String path, Map<String, String> validationErrors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = new Date().getTime();
        this.validationErrors = validationErrors;
    }
}
