package com.example.demony.bean;

public class Kc {
    private String path,name,xh,cshang,cs,kc,wz;

    public Kc(String path, String name, String xh, String cshang, String cs, String kc, String wz) {
        this.path = path;
        this.name = name;
        this.xh = xh;
        this.cshang = cshang;
        this.cs = cs;
        this.kc = kc;
        this.wz = wz;
    }

    @Override
    public String toString() {
        return "Kc{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", xh='" + xh + '\'' +
                ", cshang='" + cshang + '\'' +
                ", cs='" + cs + '\'' +
                ", kc='" + kc + '\'' +
                ", wz='" + wz + '\'' +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCshang() {
        return cshang;
    }

    public void setCshang(String cshang) {
        this.cshang = cshang;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
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

    public String getKc() {
        return kc;
    }

    public void setKc(String kc) {
        this.kc = kc;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }
}
