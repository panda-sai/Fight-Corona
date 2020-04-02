package com.e.fight_corona.models;

public class Query
{
    private String sender;
    private String query;
    private boolean isanswered;
    private  Object time;
    private String id;

    public Query(String sender, String query, boolean isanswered, Object time, String id) {
        this.sender = sender;
        this.query = query;
        this.isanswered = isanswered;
        this.time = time;
        this.id = id;
    }

    public Query()
    {

    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isIsanswered() {
        return isanswered;
    }

    public void setIsanswered(boolean isanswered) {
        this.isanswered = isanswered;
    }

    public Object getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(Object time) {
        this.time = time;
    }
}
