package com.example.finalstand;

import android.os.Parcel;
import android.os.Parcelable;

public class DriverEntryModel implements Parcelable {
    private String name;
    private String desc;
    private int img;

    public DriverEntryModel(String n, String d, int i) {
        name = n;
        desc = d;
        img = i;
    }

    protected DriverEntryModel(Parcel in) {
        name = in.readString();
        desc = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeInt(img);
    }
}
