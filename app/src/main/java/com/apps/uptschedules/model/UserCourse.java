package com.apps.uptschedules.model;

import java.util.ArrayList;

public class UserCourse {
    private String name;
    private String email;
    private String faculty;
    private String section;
    private Long year;
    private ArrayList<Long> courses = new ArrayList<>();
    private String password;

    public UserCourse() {

    }

    public UserCourse(ArrayList<Long> courses, String email, String faculty, String name, String password, String section, Long year) {
        this.name = name;
        this.email = email;
        this.faculty = faculty;
        this.section = section;
        this.year = year;
        this.courses = courses;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public ArrayList<Long> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Long> courses) {
        this.courses = courses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", faculty='" + faculty + '\'' +
                ", section='" + section + '\'' +
                ", year=" + year +
                ", courses[0]=" + courses.get(0) +
                ", password='" + password + '\'' +
                '}';
    }
}
