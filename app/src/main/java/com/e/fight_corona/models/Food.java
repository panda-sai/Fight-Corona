package com.e.fight_corona.models;

public class Food
{
    private String name;
    private String mobile_no;
    private String food_capacity;
    private String time;
    private String Date;
    private String id;
    private String location;
    public Food()
    {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Food(String name, String mobile_no, String food_capacity, String time, String date, String id) {
        this.name = name;
        this.mobile_no = mobile_no;
        this.food_capacity = food_capacity;
        this.time = time;
        Date = date;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getFood_capacity() {
        return food_capacity;
    }

    public void setFood_capacity(String food_capacity) {
        this.food_capacity = food_capacity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
