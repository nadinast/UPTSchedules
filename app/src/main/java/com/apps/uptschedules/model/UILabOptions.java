package com.apps.uptschedules.model;

import java.util.List;

public class UILabOptions {
    List<Option> options;

    private String name;
    private int courseId;

    public UILabOptions(List<Option> options, String name, int courseId){
        this.options = options;
        this.name = name;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
