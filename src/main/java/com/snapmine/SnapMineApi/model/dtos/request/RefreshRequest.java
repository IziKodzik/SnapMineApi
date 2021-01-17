package com.snapmine.SnapMineApi.model.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshRequest {

    private String token;
    private String refreshToken;

    public RefreshRequest(String token,
                          String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "RefreshRequest{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
