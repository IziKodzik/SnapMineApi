package com.snapmine.SnapMineApi.model.dtos.response;

public class LoginResponse
    extends Response{

    private String id;
    private String others;

    public LoginResponse(int code, String message) {
        super(code, message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
