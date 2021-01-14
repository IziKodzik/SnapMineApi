package com.snapmine.SnapMineApi.model.dtos.response;

import com.google.gson.annotations.Expose;

public class Response {

    @Expose
    private int status = 200;
    @Expose
    public String message;

    public Response(int code, String message) {
        this(message);
        this.status = code;
    }
    public Response(String message){
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "code=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
