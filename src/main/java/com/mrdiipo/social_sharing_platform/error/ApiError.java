package com.mrdiipo.social_sharing_platform.error;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {

    private long timeStamp = new Date().getTime();
    private int status;
    private String message;
    private String url;
    private Map<String, String> validationError;

    public ApiError(int status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url;
    }
}
