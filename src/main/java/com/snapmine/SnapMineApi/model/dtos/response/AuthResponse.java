package com.snapmine.SnapMineApi.model.dtos.response;

public class AuthResponse
    extends Response{

    public AuthResponse(String message){
        super(message);
    }
    public AuthResponse(int code, String message) {
        super(code, message);
    }
}
