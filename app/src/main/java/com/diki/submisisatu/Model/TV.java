package com.diki.submisisatu.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class TV implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("vote_average")
    private  double voteAverage;
    @SerializedName("vote_count")
    private double voteCount;
    @SerializedName("title")
    private String Title;
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("backdrop_path")
    private  String backdropPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_air_date")
    private String releaseDate;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("rating")
    private int rating;

    protected TV(Parcel in) {
        id = in.readInt();
        voteAverage = in.readDouble();
        voteCount = in.readInt();
        Title = in.readString();
        name = in.readString();
        popularity = in.readDouble();
        backdropPath = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        runtime = in.readInt();
        rating = in.readInt();
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

    public int getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getVoteCount() {
        return voteCount;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getName() {
        return name;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getRuntime() {
        return runtime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeDouble(voteAverage);
        parcel.writeString(Title);
        parcel.writeDouble(voteCount);
        parcel.writeString(name);
        parcel.writeDouble(popularity);
        parcel.writeString(backdropPath);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeInt(runtime);
    }
}
