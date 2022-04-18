package com.example.finalstand;

public class GPEntryModel {
    private String title;
    private String desc;
    private int img;

    public GPEntryModel(String t, String d, int i) {
        title = t;
        desc = d;
        img = i;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getImg() {
        return img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
