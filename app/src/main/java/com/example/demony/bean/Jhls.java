package com.example.demony.bean;

public class Jhls {
    private String jhsj,gys,sl,dl,ze,cgr,bz,yl;

    public Jhls(String jhsj, String gys, String sl, String dl, String ze, String cgr, String bz, String yl) {
        this.jhsj = jhsj;
        this.gys = gys;
        this.sl = sl;
        this.dl = dl;
        this.ze = ze;
        this.cgr = cgr;
        this.bz = bz;
        this.yl = yl;
    }

    @Override
    public String toString() {
        return "Jhls{" +
                "jhsj='" + jhsj + '\'' +
                ", gys='" + gys + '\'' +
                ", sl='" + sl + '\'' +
                ", dl='" + dl + '\'' +
                ", ze='" + ze + '\'' +
                ", cgr='" + cgr + '\'' +
                ", bz='" + bz + '\'' +
                ", yl='" + yl + '\'' +
                '}';
    }

    public String getJhsj() {
        return jhsj;
    }

    public void setJhsj(String jhsj) {
        this.jhsj = jhsj;
    }

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public String getZe() {
        return ze;
    }

    public void setZe(String ze) {
        this.ze = ze;
    }

    public String getCgr() {
        return cgr;
    }

    public void setCgr(String cgr) {
        this.cgr = cgr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getYl() {
        return yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }
}
