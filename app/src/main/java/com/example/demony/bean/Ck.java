package com.example.demony.bean;

public class Ck {
    private String path,name,xh,cshang,cs,kcl,wz;
    private boolean hx;

    public Ck(String path, String name, String xh, String cshang, String cs, String kcl, String wz, boolean hx) {
        this.path = path;
        this.name = name;
        this.xh = xh;
        this.cshang = cshang;
        this.cs = cs;
        this.kcl = kcl;
        this.wz = wz;
        this.hx = hx;
    }

    @Override
    public String toString() {
        return "Ck{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", xh='" + xh + '\'' +
                ", cshang='" + cshang + '\'' +
                ", cs='" + cs + '\'' +
                ", kcl='" + kcl + '\'' +
                ", wz='" + wz + '\'' +
                '}';
    }

    public boolean isHx() {
        return hx;
    }

    public void setHx(boolean hx) {
        this.hx = hx;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getKcl() {
        return kcl;
    }

    public void setKcl(String kcl) {
        this.kcl = kcl;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }
}
