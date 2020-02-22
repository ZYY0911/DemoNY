package com.example.demony.bean;

public class Gyslb {
    private String name,dd,ylm,bh;

    public Gyslb(String name, String dd, String ylm, String bh) {
        this.name = name;
        this.dd = dd;
        this.ylm = ylm;
        this.bh = bh;
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

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getYlm() {
        return ylm;
    }

    public void setYlm(String ylm) {
        this.ylm = ylm;
    }
}
