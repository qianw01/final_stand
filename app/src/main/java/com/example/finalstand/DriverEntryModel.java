package com.example.finalstand;

public class DriverEntryModel {
    private String name;
    private String desc;
    private int img;

    public DriverEntryModel(String n, String d, int i) {
        name = n;
        desc = d;
        img = i;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
