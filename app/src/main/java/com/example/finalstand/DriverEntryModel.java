package com.example.finalstand;

import android.os.Parcel;
import android.os.Parcelable;

public class DriverEntryModel implements Parcelable {
    private String name;
    private String desc;
    private String team;
    private String number;
    private String country;
    private String podiums;
    private String hFinish;
    private int img;

    public DriverEntryModel(String na, String d, String t, String nu, String c, String p, String hF, int i) {
        name = na;
        desc = d;
        team = t;
        number = nu;
        country = c;
        podiums = p;
        hFinish = hF;
        img = i;
    }

    protected DriverEntryModel(Parcel in) {
        name = in.readString();
        desc = in.readString();
        team = in.readString();
        number = in.readString();
        country = in.readString();
        podiums = in.readString();
        hFinish = in.readString();
        img = in.readInt();
    }

    public static final Creator<DriverEntryModel> CREATOR = new Creator<DriverEntryModel>() {
        @Override
        public DriverEntryModel createFromParcel(Parcel in) {
            return new DriverEntryModel(in);
        }

        @Override
        public DriverEntryModel[] newArray(int size) {
            return new DriverEntryModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getTeam() { return team; }

    public String getNumber() { return number; }

    public String getCountry() { return country; }

    public String getPodiums() { return podiums; }

    public String gethFinish() { return hFinish; }

    public int getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPodiums(String podiums) {
        this.podiums = podiums;
    }

    public void setHFinish(String hFinish) {
        this.hFinish = hFinish;
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
        parcel.writeString(team);
        parcel.writeString(number);
        parcel.writeString(country);
        parcel.writeString(podiums);
        parcel.writeString(hFinish);
        parcel.writeInt(img);
    }
}
