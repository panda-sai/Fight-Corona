package com.e.fight_corona.models;

public class People
{
    private String id;
    private String username;
    private boolean isDoctor;

    public People(String id, String username, boolean isDoctor)
    {
        this.id = id;
        this.username = username;
        this.isDoctor = isDoctor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }
}



