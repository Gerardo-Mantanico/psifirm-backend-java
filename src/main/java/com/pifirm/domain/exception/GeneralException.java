package com.pifirm.domain.exception;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException{
    private int status = HttpStatus.BAD_REQUEST.value();
    private final String type;
    public GeneralException(String type, String message) {
        super(message);
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public GeneralException setStatus(HttpStatus status) {
        this.status = status.value();
        return this;
    }

    public String getType() {
        return type;
    }
}
