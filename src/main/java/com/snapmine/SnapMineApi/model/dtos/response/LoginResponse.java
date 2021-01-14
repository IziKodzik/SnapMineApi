package com.snapmine.SnapMineApi.model.dtos.response;

import com.google.gson.annotations.Expose;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
import org.springframework.security.core.parameters.P;

public class LoginResponse
    extends Response{

    private String token;

    public LoginResponse(String message){
        super(message);
    }

    public LoginResponse(int code, String message){
        super(code,message);
    }
    public LoginResponse(String message,String token) {
        super(message);
        this.token = token;
    }


    public void setToken(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }


}
