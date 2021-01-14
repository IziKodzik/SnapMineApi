package com.snapmine.SnapMineApi.exception;

public class ApiRequestException
    extends RuntimeException{

    private int errorCode = 500;

    public ApiRequestException(String message) {
        super(message);
    }
    public ApiRequestException(){
        this("not prepared exception.",500);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApiRequestException(String message,int code){
        this(message);
        errorCode = code;
    }
    public ApiRequestException(String message,Throwable cause,int errorCode){
        this(message,cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
