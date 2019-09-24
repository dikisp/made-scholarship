package com.diki.submisisatu.repo;

import android.database.Cursor;

import com.diki.submisisatu.Model.FavoriteMovie;

import java.util.ArrayList;

import static android.os.Build.ID;
import static android.provider.BaseColumns._ID;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.BACKDROP_PATH;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.CATEGORY;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.ORIGINAL_LANGUAGE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.OVERVIEW;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.POSTER_URL;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.RELEASE_DATE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.TITLE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.VOTE_COUNT;

public class FavoriteRepo {

    public static ArrayList<FavoriteMovie> getMovieFavoriteList(Cursor cursor) {
        ArrayList<FavoriteMovie> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            int mId = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(POSTER_URL));
            int rating = cursor.getInt(cursor.getColumnIndexOrThrow(VOTE_COUNT));
            String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));
            String orginLang = cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_LANGUAGE));
            String backdrop = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(CATEGORY));
            if (category.equals("movie")) {
                list.add(new FavoriteMovie(id, mId,title,poster,rating,backdrop,releaseDate,overview,orginLang));
            }
        }

        return list;
    }

    public static ArrayList<FavoriteMovie> getTvFavoriteList(Cursor cursor) {
        ArrayList<FavoriteMovie> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            int mId = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(POSTER_URL));
            String rating = cursor.getString(cursor.getColumnIndexOrThrow(VOTE_COUNT));
            String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));
            String backdrop = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(CATEGORY));
            if (category.equals("tv")) {
                list.add(new FavoriteMovie(id, mId, title, poster, backdrop, rating, releaseDate, overview, category));
            }
        }

        return list;
    }
}
