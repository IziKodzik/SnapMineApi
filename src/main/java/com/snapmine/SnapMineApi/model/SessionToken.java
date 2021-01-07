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
    public SessionToken(Role... roles){
        StringBuilder token = new StringBuilder();
        System.out.println(UUID.randomUUID().toString().replace("-","").length());
        List<Role> toShuffle = Arrays.asList(roles);
        Collections.shuffle(toShuffle);
        toShuffle.toArray(roles);
        token.append(UUID.randomUUID().toString().replace("-",""));
        Arrays.stream(roles).forEach(r -> token.append(r.toString()));
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
}
