package com.example.demony.bean;

public class Gwc {
    private String name,sms,jg,xh,image;
    private int count;

    public Gwc(String name, String sms, String jg, String xh, String image, int count) {
        this.name = name;
        this.sms = sms;
        this.jg = jg;
        this.xh = xh;
        this.image = image;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Gwc{" +
                "name='" + name + '\'' +
                ", sms='" + sms + '\'' +
                ", jg='" + jg + '\'' +
                ", xh='" + xh + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
