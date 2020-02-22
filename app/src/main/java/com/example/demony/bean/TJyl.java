package com.example.demony.bean;

public class TJyl {
    private String image,mc,jg,bh;

    public TJyl(String image, String mc, String jg, String bh) {
        this.image = image;
        this.mc = mc;
        this.jg = jg;
        this.bh = bh;
    }

    @Override
    public String toString() {
        return "TJyl{" +
                "image='" + image + '\'' +
                ", mc='" + mc + '\'' +
                ", jg='" + jg + '\'' +
                '}';
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }
}
