package com.example.demony.bean;

public class Chaxun {
    private String name;

    public Chaxun(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chaxun{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
