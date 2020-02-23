package com.example.demony.bean;

public class Chjl1  {
    private String name,xh,shuliang,time,chr,jsr,qx,path,scx;


    public Chjl1(String name, String xh, String shuliang, String time, String chr, String jsr, String qx, String path, String scx) {
        this.name = name;
        this.xh = xh;
        this.shuliang = shuliang;
        this.time = time;
        this.chr = chr;
        this.jsr = jsr;
        this.qx = qx;
        this.path = path;
        this.scx = scx;
    }

    @Override
    public String toString() {
        return "Chjl1{" +
                "name='" + name + '\'' +
                ", xh='" + xh + '\'' +
                ", shuliang='" + shuliang + '\'' +
                ", time='" + time + '\'' +
                ", chr='" + chr + '\'' +
                ", jsr='" + jsr + '\'' +
                ", qx='" + qx + '\'' +
                ", path='" + path + '\'' +
                ", scx='" + scx + '\'' +
                '}';
    }

    public String getScx() {
        return scx;
    }

    public void setScx(String scx) {
        this.scx = scx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public String getJsr() {
        return jsr;
    }

    public void setJsr(String jsr) {
        this.jsr = jsr;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
