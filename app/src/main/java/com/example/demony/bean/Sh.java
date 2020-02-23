package com.example.demony.bean;

public class Sh {
    private String bh,name,xl,hy,szd,zt;
    private boolean xz;

    public Sh(String bh, String name, String xl, String hy, String szd, String zt, boolean xz) {
        this.bh = bh;
        this.name = name;
        this.xl = xl;
        this.hy = hy;
        this.szd = szd;
        this.zt = zt;
        this.xz = xz;
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

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getHy() {
        return hy;
    }

    public void setHy(String hy) {
        this.hy = hy;
    }

    public String getSzd() {
        return szd;
    }

    public void setSzd(String szd) {
        this.szd = szd;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public boolean isXz() {
        return xz;
    }

    public void setXz(boolean xz) {
        this.xz = xz;
    }
}
