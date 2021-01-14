package com.snapmine.SnapMineApi.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
    private final int status;
    private final ZonedDateTime timeStamp;



    public ApiException(String message , int status, ZonedDateTime timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }


    public int getStatus() {
        return status;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
