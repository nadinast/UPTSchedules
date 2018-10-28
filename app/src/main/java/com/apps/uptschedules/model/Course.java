package com.apps.uptschedules.model;

public class Course {
    private String abbreviation;
    private String day;
    private String hours;
    private String name;
    private String room;
    private String website;

    public Course(){

    }

    public Course(String abbreviation, String day, String hours, String name, String room, String website) {
        this.abbreviation = abbreviation;
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

    @Override
    public String toString() {
        return "Course{" +
                "abbreviation='" + abbreviation + '\'' +
                ", day='" + day + '\'' +
                ", hours='" + hours + '\'' +
                ", name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
