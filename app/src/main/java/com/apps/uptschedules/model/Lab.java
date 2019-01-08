package com.apps.uptschedules.model;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Lab implements Serializable {
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
