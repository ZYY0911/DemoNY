package com.example.demony.bean;

/**
 * Create by 张瀛煜 on 2020-02-24 ：）
 */
public class WXCLBean {

    /**
     * wxsj : 2019-2-8
     * zt : 已修
     * clbh : 202001
     * clxh : 525Li领先型
     * clwt : 燃油消耗反常
     * bxsj : 2020-02-06
     */

    private String wxsj;
    private String zt;
    private String clbh;
    private String clxh;
    private String clwt;
    private String bxsj;
    private boolean is;

    public WXCLBean(String wxsj, String zt, String clbh, String clxh, String clwt, String bxsj, boolean is) {
        this.wxsj = wxsj;
        this.zt = zt;
        this.clbh = clbh;
        this.clxh = clxh;
        this.clwt = clwt;
        this.bxsj = bxsj;
        this.is = is;
    }

    public boolean isIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public String getWxsj() {
        return wxsj;
    }

    public void setWxsj(String wxsj) {
        this.wxsj = wxsj;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getClbh() {
        return clbh;
    }

    public void setClbh(String clbh) {
        this.clbh = clbh;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getClwt() {
        return clwt;
    }

    public void setClwt(String clwt) {
        this.clwt = clwt;
    }

    public String getBxsj() {
        return bxsj;
    }

    public void setBxsj(String bxsj) {
        this.bxsj = bxsj;
    }
}
