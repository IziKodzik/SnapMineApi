package com.snapmine.SnapMineApi.model;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SessionToken {

    private String id;
    private List<Role> roles;
    private DateTime expirationDay;
    private String refreshCode;

    public SessionToken(String id) {
        this.id = id;
    }
    public SessionToken(List<Role> roles){
        this.expirationDay = DateTime.now().plusHours(1);
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

    public DateTime getExpirationDay() {
        return expirationDay;
    }

    public void setExpirationDay(DateTime expirationDay) {
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
