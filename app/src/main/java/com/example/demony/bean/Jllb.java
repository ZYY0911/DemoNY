package com.example.demony.bean;

public class Jllb {
    private String mc,bz,time;

    public Jllb(String mc, String bz, String time) {
        this.mc = mc;
        this.bz = bz;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Jllb{" +
                "mc='" + mc + '\'' +
                ", bz='" + bz + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
