package com.example.lib.annotation;

public class TestModel {
    @MyField(description = "姓名")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
