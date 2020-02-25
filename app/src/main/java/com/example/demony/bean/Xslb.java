package com.example.demony.bean;

public class Xslb {
    private String cjh,scxh,name,xh,sl;

    public Xslb(String cjh, String scxh, String name, String xh, String sl) {
        this.cjh = cjh;
        this.scxh = scxh;
        this.name = name;
        this.xh = xh;
        this.sl = sl;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "cjh='" + cjh + '\'' +
                ", scxh='" + scxh + '\'' +
                ", name='" + name + '\'' +
                ", xh='" + xh + '\'' +
                ", sl='" + sl + '\'' +
                '}';
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getScxh() {
        return scxh;
    }

    public void setScxh(String scxh) {
        this.scxh = scxh;
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

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }
}
