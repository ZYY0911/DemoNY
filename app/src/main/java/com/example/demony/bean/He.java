package com.example.demony.bean;

public class He {
    private String dj,sl;
    private int position;

    public He(String dj, String sl, int position) {
        this.dj = dj;
        this.sl = sl;
        this.position = position;
    }

    @Override
    public String toString() {
        return "He{" +
                "dj='" + dj + '\'' +
                ", sl='" + sl + '\'' +
                '}';
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }
}
