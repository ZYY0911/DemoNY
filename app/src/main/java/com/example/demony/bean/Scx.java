package com.example.demony.bean;

public class Scx {
    private String cjm,scxm,zt,hj,ts;

    public Scx(String cjm, String scxm, String zt, String hj, String ts) {
        this.cjm = cjm;
        this.scxm = scxm;
        this.zt = zt;
        this.hj = hj;
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Scx{" +
                "cjm='" + cjm + '\'' +
                ", scxm='" + scxm + '\'' +
                ", zt='" + zt + '\'' +
                ", hj='" + hj + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }

    public String getCjm() {
        return cjm;
    }

    public void setCjm(String cjm) {
        this.cjm = cjm;
    }

    public String getScxm() {
        return scxm;
    }

    public void setScxm(String scxm) {
        this.scxm = scxm;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
