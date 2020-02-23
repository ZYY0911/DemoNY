package com.example.demony.bean;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class Z_Sp {

    /**
     * gssbh : 10001
     * ylmc : 生铁
     * ylbh : 1-1A
     * jg : 10元/斤
     * path : tie
     */

    private String gssbh;
    private String ylmc;
    private String ylbh;
    private String jg;
    private String path;

    public Z_Sp(String gssbh, String ylmc, String ylbh, String jg, String path) {
        this.gssbh = gssbh;
        this.ylmc = ylmc;
        this.ylbh = ylbh;
        this.jg = jg;
        this.path = path;
    }

    public String getGssbh() {
        return gssbh;
    }

    public void setGssbh(String gssbh) {
        this.gssbh = gssbh;
    }

    public String getYlmc() {
        return ylmc;
    }

    public void setYlmc(String ylmc) {
        this.ylmc = ylmc;
    }

    public String getYlbh() {
        return ylbh;
    }

    public void setYlbh(String ylbh) {
        this.ylbh = ylbh;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
