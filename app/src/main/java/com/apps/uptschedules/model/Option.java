package com.apps.uptschedules.model;

import java.io.Serializable;

public class Option implements Serializable {
    private String day;
    private String hours;
    private String room;
    private Long capacity;


    public Option(){

    }

    public Option(String day, String hours, String room, Long capacity) {
        this.day = day;
        this.hours = hours;
        this.room = room;
        this.capacity = capacity;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
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
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
