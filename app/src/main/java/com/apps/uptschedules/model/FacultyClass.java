package com.apps.uptschedules.model;

import java.util.List;
import java.util.Map;

public class FacultyClass {
    public String abbreviation;
    public String day;
    public String hours;
    public String name;
    public String room;
    public String website;

    private List<Option> options;

    public FacultyClass() {

    }

    public FacultyClass(String abbreviation, String day, String hours, String name, String room, String website, List<Option> options) {
        this.abbreviation = abbreviation;
        this.day = day;
        this.hours = hours;
        this.name = name;
        this.room = room;
        this.website = website;
        this.options = options;
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "FacultyClass{" +
                "abbreviation='" + abbreviation + '\'' +
                ", day='" + day + '\'' +
                ", hours='" + hours + '\'' +
                ", name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", website='" + website + '\'' +
                ", options=" + options +
                '}';
    }
}
