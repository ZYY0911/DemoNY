package com.example.demony.bean;

import org.litepal.crud.LitePalSupport;

public class Sgxz extends LitePalSupport {
    private String name,xh,gys,shuliang,yuliang,dj,weizhi,caigoyuan,lianxiren,zhanghao,ren,time,path;

    public Sgxz(String name, String xh, String gys, String shuliang, String yuliang, String dj, String weizhi, String caigoyuan, String lianxiren, String zhanghao, String ren, String time, String path) {
        this.name = name;
        this.xh = xh;
        this.gys = gys;
        this.shuliang = shuliang;
        this.yuliang = yuliang;
        this.dj = dj;
        this.weizhi = weizhi;
        this.caigoyuan = caigoyuan;
        this.lianxiren = lianxiren;
        this.zhanghao = zhanghao;
        this.ren = ren;
        this.time = time;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Sgxz{" +
                "name='" + name + '\'' +
                ", xh='" + xh + '\'' +
                ", gys='" + gys + '\'' +
                ", shuliang='" + shuliang + '\'' +
                ", yuliang='" + yuliang + '\'' +
                ", dj='" + dj + '\'' +
                ", weizhi='" + weizhi + '\'' +
                ", caigoyuan='" + caigoyuan + '\'' +
                ", lianxiren='" + lianxiren + '\'' +
                ", zhanghao='" + zhanghao + '\'' +
                ", ren='" + ren + '\'' +
                ", time='" + time + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public String getYuliang() {
        return yuliang;
    }

    public void setYuliang(String yuliang) {
        this.yuliang = yuliang;
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

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getWeizhi() {
        return weizhi;
    }

    public void setWeizhi(String weizhi) {
        this.weizhi = weizhi;
    }

    public String getCaigoyuan() {
        return caigoyuan;
    }

    public void setCaigoyuan(String caigoyuan) {
        this.caigoyuan = caigoyuan;
    }

    public String getLianxiren() {
        return lianxiren;
    }

    public void setLianxiren(String lianxiren) {
        this.lianxiren = lianxiren;
    }

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    public String getRen() {
        return ren;
    }

    public void setRen(String ren) {
        this.ren = ren;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
