package com.snapmine.SnapMineApi.model.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    private String name,password;

    public LoginRequest(){

    }
    public LoginRequest(@JsonProperty String name, @JsonProperty String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
