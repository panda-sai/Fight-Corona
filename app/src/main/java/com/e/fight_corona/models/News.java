package com.e.fight_corona.models;

public class News
{
    private String sender;
    private String news;
    private boolean isanswered;
    private  Object time;
    private String id;
    private String answer;
    private String answered_collaborators;
    public News()
    {

    }

    public News(String sender, String news, boolean isanswered, Object time, String id) {
        this.sender = sender;
        this.news = news;
        this.isanswered = isanswered;
        this.time = time;
        this.id = id;
    }

    public News(String sender, String news, boolean isanswered, Object time, String id, String answer, String answered_collaborators) {
        this.sender = sender;
        this.news = news;
        this.isanswered = isanswered;
        this.time = time;
        this.id = id;
        this.answer = answer;
        this.answered_collaborators = answered_collaborators;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
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

    public void setTime(Object time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswered_collaborators() {
        return answered_collaborators;
    }

    public void setAnswered_collaborators(String answered_collaborators) {
        this.answered_collaborators = answered_collaborators;
    }
}
