package com.apps.uptschedules.model;

import java.util.List;
import java.util.Map;

public class FacultyClass {
    private Course course;
    private Map<String, List<Option>> labs;

    public FacultyClass(){

    }

    public FacultyClass(Course course,Map<String, List<Option>> labs) {
        this.course = course;
        this.labs = labs;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Map<String, List<Option>> getLabs() {
        return labs;
    }

    public void setLabs(Map<String, List<Option>> labs) {
        this.labs = labs;
    }

    @Override
    public String toString() {
        return "FacultyClass{" +
                "course=" + course +
                ", labs=" + labs +
                '}';
    }
}
