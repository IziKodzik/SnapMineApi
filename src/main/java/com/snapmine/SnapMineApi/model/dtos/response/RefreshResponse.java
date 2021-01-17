package com.snapmine.SnapMineApi.model.dtos.response;

public class RefreshResponse
    extends Response{

    String token;

    public RefreshResponse(int code, String message) {
        super(code, message);
    }

    public RefreshResponse(String message) {
        super(message);
    }
}
