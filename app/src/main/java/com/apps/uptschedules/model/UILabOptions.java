package com.apps.uptschedules.model;

import java.util.List;

public class UILabOptions {
    List<Option> options;

    private String name;

    public UILabOptions(List<Option> options, String name){
        this.options = options;
        this.name = name;
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
}
