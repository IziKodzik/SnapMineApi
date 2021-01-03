package com.snapmine.SnapMineApi.model.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    private String login,password;

    public LoginRequest(){

    }
    public LoginRequest(@JsonProperty String login, @JsonProperty String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
