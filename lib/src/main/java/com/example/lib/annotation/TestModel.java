package com.example.lib.annotation;

public class TestModel {
    @MyField(description = "姓名")
    private String name;

    private int length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
