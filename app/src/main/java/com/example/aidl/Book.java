package com.example.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String name;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeFloat(this.price);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.price = source.readFloat();
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.name = in.readString();
        this.price = in.readFloat();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
