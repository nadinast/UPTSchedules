package com.apps.uptschedules.model;

public class Option {
    private String day;
    private String hours;
    private String room;

    public Option(){

    }

    public Option(String day, String hours, String room) {
        this.day = day;
        this.hours = hours;
        this.room = room;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Option{" +
                "day='" + day + '\'' +
                ", hours='" + hours + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
