package com.example.demony.bean;

public class Zp {
    private String bh,zt,name,xl,hy,szd,gsdz,tel,email,gw,xz,zyyq,gzjlyq,gwtq,shr,shsj,time;
    private boolean hx,sc;

    public Zp(String bh, String zt, String name, String xl, String hy, String szd, String gsdz, String tel, String email, String gw, String xz, String zyyq, String gzjlyq, String gwtq, String shr, String shsj, String time, boolean hx, boolean sc) {
        this.bh = bh;
        this.zt = zt;
        this.name = name;
        this.xl = xl;
        this.hy = hy;
        this.szd = szd;
        this.gsdz = gsdz;
        this.tel = tel;
        this.email = email;
        this.gw = gw;
        this.xz = xz;
        this.zyyq = zyyq;
        this.gzjlyq = gzjlyq;
        this.gwtq = gwtq;
        this.shr = shr;
        this.shsj = shsj;
        this.time = time;
        this.hx = hx;
        this.sc = sc;
    }

    @Override
    public String toString() {
        return "Zp{" +
                "bh='" + bh + '\'' +
                ", zt='" + zt + '\'' +
                ", name='" + name + '\'' +
                ", xl='" + xl + '\'' +
                ", hy='" + hy + '\'' +
                ", szd='" + szd + '\'' +
                ", gsdz='" + gsdz + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", gw='" + gw + '\'' +
                ", xz='" + xz + '\'' +
                ", zyyq='" + zyyq + '\'' +
                ", gzjlyq='" + gzjlyq + '\'' +
                ", gwtq='" + gwtq + '\'' +
                ", shr='" + shr + '\'' +
                ", shsj='" + shsj + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public boolean isHx() {
        return hx;
    }

    public void setHx(boolean hx) {
        this.hx = hx;
    }

    public boolean isSc() {
        return sc;
    }

    public void setSc(boolean sc) {
        this.sc = sc;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
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

    public String getGsdz() {
        return gsdz;
    }

    public void setGsdz(String gsdz) {
        this.gsdz = gsdz;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getGzjlyq() {
        return gzjlyq;
    }

    public void setGzjlyq(String gzjlyq) {
        this.gzjlyq = gzjlyq;
    }

    public String getGwtq() {
        return gwtq;
    }

    public void setGwtq(String gwtq) {
        this.gwtq = gwtq;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getShsj() {
        return shsj;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
