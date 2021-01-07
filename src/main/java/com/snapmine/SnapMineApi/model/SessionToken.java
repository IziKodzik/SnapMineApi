package com.snapmine.SnapMineApi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SessionToken {

    private String token;


    public SessionToken(String token) {
        this.token = token;
    }
    public SessionToken(List<Role> roles){

        StringBuilder token = new StringBuilder();
        String id = UUID.randomUUID().toString().replace("-","");
        token.append(id);
        Collections.shuffle(roles);
        roles.forEach(r->token.append(r.toString()));
        this.token = token.toString();
    }
    public SessionToken(Role... roles){
        this(Arrays.asList(roles));
    }

    public String getId(){
        return token.substring(0,32);
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SessionToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
