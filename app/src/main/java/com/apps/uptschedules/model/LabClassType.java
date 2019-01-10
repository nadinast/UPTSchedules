package com.apps.uptschedules.model;

public class LabClassType implements ClassType {
    public String hours;
    public String abbreviation;
    public String type;
    public String room;

    public LabClassType(String hours, String abbreviation, String type, String room) {
        this.hours = hours;
        this.abbreviation = abbreviation;
        this.type = type;
        this.room = room;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "LabClassType{" +
                "hours='" + hours + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", type='" + type + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
