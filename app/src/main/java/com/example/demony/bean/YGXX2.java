package com.example.demony.bean;

import java.io.Serializable;

/**
 * Create by 张瀛煜 on 2020-02-23 ：）
 * 姓名、性别、出生日期、电话、生产线、职务、邮箱、家庭住址
 */
public class YGXX2 implements Serializable {
    private int id;
    private String name,sex,birth,tel,scx,zw,email,address;
    private String pingying;
    private boolean xz;

    public boolean isXz() {
        return xz;
    }

    public void setXz(boolean xz) {
        this.xz = xz;
    }

    public String getPingying() {
        return pingying;
    }

    public void setPingying(String pingying) {
        this.pingying = pingying;
    }

    public YGXX2(String name, String sex, String birth, String tel, String scx, String zw, String email, String address,boolean xz) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.tel = tel;
        this.scx = scx;
        this.zw = zw;
        this.email = email;
        this.address = address;
        this.xz = xz;
    }

    @Override
    public String toString() {
        return "YGXX{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", tel='" + tel + '\'' +
                ", scx='" + scx + '\'' +
                ", zw='" + zw + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getScx() {
        return scx;
    }

    public void setScx(String scx) {
        this.scx = scx;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
