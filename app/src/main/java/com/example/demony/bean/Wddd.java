package com.example.demony.bean;

public class Wddd {
    private String name,jg,time;

    public Wddd(String name, String jg, String time) {
        this.name = name;
        this.jg = jg;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Wddd{" +
                "name='" + name + '\'' +
                ", jg='" + jg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
