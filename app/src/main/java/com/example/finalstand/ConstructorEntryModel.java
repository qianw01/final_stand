package com.example.finalstand;

import android.os.Parcel;
import android.os.Parcelable;

public class ConstructorEntryModel implements Parcelable {
    private String name;
    private String desc;
    private String driver1;
    private String driver2;
    private int img;

    public ConstructorEntryModel(String n, String d, String d1, String d2, int i) {
        name = n;
        desc = d;
        driver1 = d1;
        driver2 = d2;
        img = i;
    }

    protected ConstructorEntryModel(Parcel in) {
        name = in.readString();
        desc = in.readString();
        driver1 = in.readString();
        driver2 = in.readString();
        img = in.readInt();
    }

    public static final Creator<ConstructorEntryModel> CREATOR = new Creator<ConstructorEntryModel>() {
        @Override
        public ConstructorEntryModel createFromParcel(Parcel in) {
            return new ConstructorEntryModel(in);
        }

        @Override
        public ConstructorEntryModel[] newArray(int size) {
            return new ConstructorEntryModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getDriver1() {
        return driver1;
    }

    public String getDriver2() {
        return driver2;
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

    public void setDriver1(String d1) {
        this.driver1 = d1;
    }

    public void setDriver2(String d2) {
        this.driver2 = d2;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeString(driver1);
        parcel.writeString(driver2);
        parcel.writeInt(img);
    }
}
