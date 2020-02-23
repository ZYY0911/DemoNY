package com.example.demony.bean;

/**
 * Create by 张瀛煜 on 2020-02-22 ：）
 */
public class Z_Jxx {

    /**

     * {
     *        * gysbh : 10001
     *      * mc : 经销商-1
     *      * cs : 济南
     *      * dd : 槐荫区
     *      * fr : 李四
     *      * lxr : 张三
     *      * tel : 186565554421
     *      * ywfw : 车门
     *      * image : car1
     *         },
     *         {
     *             "gysbh": "10021",
     *             "mc": "经销商-2",
     *             "cs": "德州",
     *             "dd": "长河大道",
     *             "fr": "王四",
     *             "lxr": "宝三",
     *             "tel": "186522222421",
     *             "ywfw": "车灯",
     *             "image": "car2"
     *         },
     *         {
     *             "gysbh": "10023",
     *             "mc": "经销商-3",
     *             "cs": "东营",
     *             "dd": "云门山路",
     *             "fr": "杨四",
     *             "lxr": "鹏三",
     *             "tel": "14422222421",
     *             "ywfw": "机油",
     *             "image": "car3"
     *         },
     *         {
     *             "gysbh": "10024",
     *             "mc": "经销商-4",
     *             "cs": "潍坊",
     *             "dd": "潍城区",
     *             "fr": "刘四",
     *             "lxr": "齐三",
     *             "tel": "144224442421",
     *             "ywfw": "轮胎",
     *             "image": "car4"
     *         }
     */

    private String gysbh;
    private String mc;
    private String cs;
    private String dd;
    private String fr;
    private String lxr;
    private String tel;
    private String ywfw;
    private String image;

    public Z_Jxx(String gysbh, String mc, String cs, String dd, String fr, String lxr, String tel, String ywfw, String image) {
        this.gysbh = gysbh;
        this.mc = mc;
        this.cs = cs;
        this.dd = dd;
        this.fr = fr;
        this.lxr = lxr;
        this.tel = tel;
        this.ywfw = ywfw;
        this.image = image;
    }

    public String getGysbh() {
        return gysbh;
    }

    public void setGysbh(String gysbh) {
        this.gysbh = gysbh;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getYwfw() {
        return ywfw;
    }

    public void setYwfw(String ywfw) {
        this.ywfw = ywfw;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
