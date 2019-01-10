package com.apps.uptschedules.model;

public class CourseClassType implements ClassType {
    public String hours;
    public String abbreviation;
    public String type;
    public String room;
    public String profName;

    public CourseClassType(String hours, String abbreviation, String type, String room, String profName) {
        this.hours = hours;
        this.abbreviation = abbreviation;
        this.type = type;
        this.room = room;
        this.profName = profName;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CourseClassType{" +
                "hours='" + hours + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", type='" + type + '\'' +
                ", room='" + room + '\'' +
                ", profName='" + profName + '\'' +
                '}';
    }
}
