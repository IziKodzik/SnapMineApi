package com.snapmine.SnapMineApi.model.dtos.response;

import com.google.gson.annotations.Expose;

public class LoginResponse
    extends Response{

    @Expose
    private String id;
    @Expose
    private String roles;
    @Expose
    private String expirationDate;


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
        return roles;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
