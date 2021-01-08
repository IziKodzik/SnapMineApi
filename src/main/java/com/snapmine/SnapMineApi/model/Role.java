package com.snapmine.SnapMineApi.model;

public class Role {

    private int id;
    private String name,
                    value;

    private Role(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static SQLMapper<Role> getMapper(){
        return set -> new Role(set.getInt("id"),set.getString("name"),set.getString("value"));
    }

    @Override
    public String toString() {
        return "{" +
                "'name':'" + name + '\'' +
                '}';
    }
}
