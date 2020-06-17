package com.app.group15.courseManagement;

import com.app.group15.persistence.Persistence;

public class Course extends Persistence {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
