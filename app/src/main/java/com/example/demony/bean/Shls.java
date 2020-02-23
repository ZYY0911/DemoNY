package com.example.demony.bean;

public class Shls {
    private String bh,name,ge,shr,shsj;

    public Shls(String bh, String name, String ge, String shr, String shsj) {
        this.bh = bh;
        this.name = name;
        this.ge = ge;
        this.shr = shr;
        this.shsj = shsj;
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

    public String getGe() {
        return ge;
    }

    public void setGe(String ge) {
        this.ge = ge;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getShsj() {
        return shsj;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
    }
}
