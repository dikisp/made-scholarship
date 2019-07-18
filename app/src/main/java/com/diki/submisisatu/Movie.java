package com.diki.submisisatu;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {
    private String title;
    private String poster;
    private String genre;
    private String deskripsi;
    private String pruduksi;
    private String realese;
    private String income;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPruduksi() {
        return pruduksi;
    }

    public void setPruduksi(String pruduksi) {
        this.pruduksi = pruduksi;
    }

    public String getRealese() {
        return realese;
    }

    public void setRealese(String realese) {
        this.realese = realese;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;
    public Movie() {
    }



    @Override
    public int describeContents() {
        return 0;
    }


    protected Movie(Parcel in) {
        this.title = in.readString();
        this.genre = in.readString();
        this.poster = in.readString();
        this.deskripsi = in.readString();
        this.realese = in.readString();
        this.pruduksi = in.readString();
        this.rating = in.readString();
        this.income = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(genre);
        parcel.writeString(poster);
        parcel.writeString(deskripsi);
        parcel.writeString(realese);
        parcel.writeString(pruduksi);
        parcel.writeString(rating);
        parcel.writeString(income);
    }
}
