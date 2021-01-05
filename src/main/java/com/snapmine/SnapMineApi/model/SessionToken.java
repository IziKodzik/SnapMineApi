package com.snapmine.SnapMineApi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SessionToken {

    private String token;

    public SessionToken(String token) {
        this.token = token;
    }
    public SessionToken(Role... roles){
        System.out.println(Arrays.toString(roles));
        List<Role> toShuffle = Arrays.asList(roles);
        Collections.shuffle(toShuffle);
        toShuffle.toArray(roles);
        System.out.println(Arrays.toString(roles));
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
