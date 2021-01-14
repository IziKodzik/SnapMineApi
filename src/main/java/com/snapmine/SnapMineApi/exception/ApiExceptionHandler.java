package com.snapmine.SnapMineApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<?> handlerApiException(ApiRequestException e){
        ApiException r = new ApiException(
                e.getMessage(),
                e.getErrorCode(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(r,HttpStatus.valueOf(e.getErrorCode()));
    }

}
