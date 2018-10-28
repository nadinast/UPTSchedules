package com.apps.uptschedules.model;


import java.util.List;

public class Lab {
    private List<Option> options;

    public Lab(){

    }

    public Lab(List<Option> options) {
        this.options = options;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "options=" + options +
                '}';
    }
}
