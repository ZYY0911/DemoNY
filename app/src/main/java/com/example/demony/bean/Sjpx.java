package com.example.demony.bean;

public class Sjpx {
    private String time,dj,sl,zje,zh,cgy,lxr,csm,clm;

    public Sjpx(String time, String dj, String sl, String zje, String zh, String cgy, String lxr, String csm, String clm) {
        this.time = time;
        this.dj = dj;
        this.sl = sl;
        this.zje = zje;
        this.zh = zh;
        this.cgy = cgy;
        this.lxr = lxr;
        this.csm = csm;
        this.clm = clm;
    }

    @Override
    public String toString() {
        return "Jy{" +
                "time='" + time + '\'' +
                ", dj='" + dj + '\'' +
                ", sl='" + sl + '\'' +
                ", zje='" + zje + '\'' +
                ", zh='" + zh + '\'' +
                ", cgy='" + cgy + '\'' +
                ", lxr='" + lxr + '\'' +
                '}';
    }

    public String getCsm() {
        return csm;
    }

    public void setCsm(String csm) {
        this.csm = csm;
    }

    public String getClm() {
        return clm;
    }

    public void setClm(String clm) {
        this.clm = clm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getZje() {
        return zje;
    }

    public void setZje(String zje) {
        this.zje = zje;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getCgy() {
        return cgy;
    }

    public void setCgy(String cgy) {
        this.cgy = cgy;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }
}
