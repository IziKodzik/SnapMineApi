package com.snapmine.SnapMineApi.model;


import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SessionToken {

    private String id;
    private List<Role> roles;
    private Timestamp expirationDay;
    private String refreshCode;

    public SessionToken(String id) {
        this.id = id;
    }
    public SessionToken(List<Role> roles){
        this.expirationDay = new Timestamp(DateTime.now().plusHours(1).getMillis());
        this.refreshCode= UUID.randomUUID().toString().replace("-","");
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

    public Timestamp getExpirationTime() {
        return expirationDay;
    }

    public void setExpirationTime(Timestamp expirationDay) {
        this.expirationDay = expirationDay;
    }

    public String getRefreshCode() {
        return refreshCode;
    }

    public void setRefreshCode(String refreshCode) {
        this.refreshCode = refreshCode;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
