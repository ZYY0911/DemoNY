package com.example.demony.bean;

public class Jl {
    private String bh,name,xl,hy,szd,email,gw,xz,zyyq,fbsj,ypsj;

    public Jl(String bh, String name, String xl, String hy, String szd, String email, String gw, String xz, String zyyq, String fbsj, String ypsj) {
        this.bh = bh;
        this.name = name;
        this.xl = xl;
        this.hy = hy;
        this.szd = szd;
        this.email = email;
        this.gw = gw;
        this.xz = xz;
        this.zyyq = zyyq;
        this.fbsj = fbsj;
        this.ypsj = ypsj;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "bh='" + bh + '\'' +
                ", name='" + name + '\'' +
                ", xl='" + xl + '\'' +
                ", hy='" + hy + '\'' +
                ", szd='" + szd + '\'' +
                ", email='" + email + '\'' +
                ", gw='" + gw + '\'' +
                ", xz='" + xz + '\'' +
                ", zyyq='" + zyyq + '\'' +
                ", fbsj='" + fbsj + '\'' +
                ", ypsj='" + ypsj + '\'' +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public String getZyyq() {
        return zyyq;
    }

    public void setZyyq(String zyyq) {
        this.zyyq = zyyq;
    }

    public String getFbsj() {
        return fbsj;
    }

    public void setFbsj(String fbsj) {
        this.fbsj = fbsj;
    }

    public String getYpsj() {
        return ypsj;
    }

    public void setYpsj(String ypsj) {
        this.ypsj = ypsj;
    }
}
