package com.snapmine.SnapMineApi.model.dtos.response;
public class LoginResponse
    extends Response{

    private String token;
    private String refreshToken;


    public LoginResponse(int code, String message){
        super(code,message);
    }
    public LoginResponse(String message,String token,String refreshToken) {
        super(message);
        this.token = token;
        this.refreshToken = refreshToken;
    }


    public void setToken(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
