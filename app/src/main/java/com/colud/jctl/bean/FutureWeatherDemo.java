package com.colud.jctl.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/3/14.
 */

public class FutureWeatherDemo implements Serializable, Parcelable {
    private static final long serialVersionUID = -8778331469848084391L;


    public FutureWeatherDemo(String day, String date, String weather, int img) {
        this.day = day;
        this.date = date;
        this.weather = weather;
        this.img = img;
    }


    private String day;
    private String date;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    private String weather;
    private int img;

    @Override
    public String toString() {
        return "FutureWeatherDemo{" +
                "day='" + day + '\'' +
                ", date='" + date + '\'' +
                ", weather='" + weather + '\'' +
                ", img=" + img +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.day);
        dest.writeString(this.date);
        dest.writeString(this.weather);
        dest.writeInt(this.img);
    }

    public FutureWeatherDemo() {
    }

    protected FutureWeatherDemo(Parcel in) {
        this.day = in.readString();
        this.date = in.readString();
        this.weather = in.readString();
        this.img = in.readInt();
    }

    public static final Parcelable.Creator<FutureWeatherDemo> CREATOR = new Parcelable.Creator<FutureWeatherDemo>() {
        @Override
        public FutureWeatherDemo createFromParcel(Parcel source) {
            return new FutureWeatherDemo(source);
        }

        @Override
        public FutureWeatherDemo[] newArray(int size) {
            return new FutureWeatherDemo[size];
        }
    };
}
