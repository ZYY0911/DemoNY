package com.example.demony.bean;

public class Clkc {
    private String name,clxh,jb,cs,lx,hbbz,sssj,jg,sl,cms,cppz,video,image;

    public Clkc(String name, String clxh, String jb, String cs, String lx, String hbbz, String sssj, String jg, String sl, String cms, String cppz, String video, String image) {
        this.name = name;
        this.clxh = clxh;
        this.jb = jb;
        this.cs = cs;
        this.lx = lx;
        this.hbbz = hbbz;
        this.sssj = sssj;
        this.jg = jg;
        this.sl = sl;
        this.cms = cms;
        this.cppz = cppz;
        this.video = video;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Clkc{" +
                "name='" + name + '\'' +
                ", clxh='" + clxh + '\'' +
                ", jb='" + jb + '\'' +
                ", cs='" + cs + '\'' +
                ", lx='" + lx + '\'' +
                ", hbbz='" + hbbz + '\'' +
                ", sssj='" + sssj + '\'' +
                ", jg='" + jg + '\'' +
                ", sl='" + sl + '\'' +
                ", cms='" + cms + '\'' +
                ", cppz='" + cppz + '\'' +
                ", video='" + video + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getJb() {
        return jb;
    }

    public void setJb(String jb) {
        this.jb = jb;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getHbbz() {
        return hbbz;
    }

    public void setHbbz(String hbbz) {
        this.hbbz = hbbz;
    }

    public String getSssj() {
        return sssj;
    }

    public void setSssj(String sssj) {
        this.sssj = sssj;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getCms() {
        return cms;
    }

    public void setCms(String cms) {
        this.cms = cms;
    }

    public String getCppz() {
        return cppz;
    }

    public void setCppz(String cppz) {
        this.cppz = cppz;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
