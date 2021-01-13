package com.snapmine.SnapMineApi.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
    private final Throwable throwable;
    private final HttpStatus status;
    private final ZonedDateTime timeStamp;

    public ApiException(String message, Throwable throwable, HttpStatus status, ZonedDateTime timeStamp) {
        this.message = message;
        this.throwable = throwable;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
