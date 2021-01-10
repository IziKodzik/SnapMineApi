package com.snapmine.SnapMineApi.model.entity;


import ch.qos.logback.core.subst.Token;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SessionToken {

    @Expose
    private String id;
    @Expose
    private List<Role> roles;
    @Expose
    private Timestamp expirationDay;
    private String refreshCode;

    public SessionToken(){}

    public SessionToken(String hash){
        this.id = hash;
    }

    public SessionToken(@JsonProperty String id,
                        @JsonProperty List<Role> roles,
                        @JsonProperty Timestamp expirationDay,
                        @JsonProperty String refreshCode) {
        this.id = id;
        this.roles = roles;
        this.expirationDay = expirationDay;
        this.refreshCode = refreshCode;
    }

    public SessionToken(String id, List<Role> roles) {
        this.id = id;
        this.roles = roles;
    }

    public SessionToken(List<Role> roles){
        Collections.shuffle(roles);
        this.roles =roles;
        this.expirationDay = new Timestamp(DateTime.now().plusHours(1).getMillis());
        this.refreshCode= UUID.randomUUID().toString().replace("-","");
        this.id = UUID.randomUUID().toString().replace("-","");

    }
    public SessionToken(Role... roles){
        this(Arrays.asList(roles));
    }

    public boolean isUpToDate(){


        return new DateTime(expirationDay).isAfter(DateTime.now());
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

    public static SQLMapper<SessionToken> getMapper(){
        return resultSet -> new SessionToken(resultSet.getString("hash"));
    }
}
