package com.snapmine.SnapMineApi.model;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SessionToken {

    private String id;
    private List<Role> roles;

    public SessionToken(String id) {
        this.id = id;
    }
    public SessionToken(List<Role> roles){
        this.id = UUID.randomUUID().toString().replace("-","");
        this.roles = roles;
    }
    public SessionToken(Role... roles){
        this(Arrays.asList(roles));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
