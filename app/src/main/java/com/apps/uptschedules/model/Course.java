package com.apps.uptschedules.model;

import java.io.Serializable;

public class Course implements Serializable {
    private String abbreviation;
    private String day;
    private String hours;
    private String name;
    private String room;
    private String profName;
    private String website;

    public Course(){

    }

    public Course(String abbreviation, String day, String hours, String name, String room, String profName, String website) {
        this.abbreviation = abbreviation;
        this.profName =profName;
        this.day = day;
        this.hours = hours;
        this.name = name;
        this.room = room;
        this.website = website;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "abbreviation='" + abbreviation + '\'' +
                ", day='" + day + '\'' +
                ", hours='" + hours + '\'' +
                ", name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", profName='" + profName + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
