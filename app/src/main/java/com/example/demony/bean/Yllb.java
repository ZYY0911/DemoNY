package com.example.demony.bean;

import org.litepal.crud.LitePalSupport;

public class Yllb extends LitePalSupport {
    private String name,xinghao,changshang,chengshi;
    private String path;

    public Yllb(String name, String xinghao, String changshang, String chengshi, String path) {
        this.name = name;
        this.xinghao = xinghao;
        this.changshang = changshang;
        this.chengshi = chengshi;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Yllb{" +
                "name='" + name + '\'' +
                ", xinghao='" + xinghao + '\'' +
                ", changshang='" + changshang + '\'' +
                ", chengshi='" + chengshi + '\'' +
                ", path=" + path +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public String getChangshang() {
        return changshang;
    }

    public void setChangshang(String changshang) {
        this.changshang = changshang;
    }

    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
