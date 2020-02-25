package com.example.demony.bean;

public class Fs {
    private String bh,name,time;

    public Fs(String bh, String name, String time) {
        this.bh = bh;
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Fs{" +
                "bh='" + bh + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
