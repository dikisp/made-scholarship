package com.diki.submisisatu.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "tMovie")
public class DataFavoriteMovie  {
    public DataFavoriteMovie(){

    }
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "vote_average")
    public String vote_average;

    @ColumnInfo(name = "OriginalTitle")
    public String OriginalTitle;

    @ColumnInfo(name = "PosterPath")
    public String PosterPath;

    @ColumnInfo(name = "rating")
    public Double rating;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "release_date")
    public String release_date;

    @ColumnInfo(name = "movieid")
    public int movieid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOriginalTitle() {
        return OriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        OriginalTitle = originalTitle;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }


    @Ignore
    public  DataFavoriteMovie(int movieid, String originalTitle, Double rating, String posterPath, String overview){
        this.movieid = movieid;
        this.rating = rating;
        this.OriginalTitle = originalTitle;
        this.PosterPath = originalTitle;
        this.PosterPath = posterPath;
        this.overview = overview;
    }
    public  DataFavoriteMovie(int id, String originalTitle, String posterPath, String overview){
        this.id = id;
        this.movieid = movieid;
        this.rating = rating;
        this.OriginalTitle = originalTitle;
        this.PosterPath = originalTitle;
        this.PosterPath = posterPath;
        this.overview = overview;
    }
}
