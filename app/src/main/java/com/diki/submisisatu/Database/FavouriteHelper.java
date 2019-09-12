package com.diki.submisisatu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.diki.submisisatu.Model.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.BACKDROP_URL;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.HOMEPAGE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.MOVIE_ID;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.ORIGINAL_LANGUAGE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.OVERVIEW;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.POSTER_URL;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.RELEASE_DATE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.RUNTIME;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.STATUS;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.TAGLINE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.TITLE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.VOTE_AVERAGE;
import static com.diki.submisisatu.Database.DatabaseContract.TABLE_FAVOURITE;

/**
 * Created by Alvin Tandiardi on 05/09/2018.
 */

public class FavouriteHelper {

    private static String DATABASE_TABLE = TABLE_FAVOURITE;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public FavouriteHelper(Context context) {
        this.context = context;
    }

    public FavouriteHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<Movie> query() {
        ArrayList<Movie> arrayList = new ArrayList<Movie>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, _ID + " DESC"
                , null);
        cursor.moveToFirst();
        Movie Movie;
        if (cursor.getCount() > 0) {
            do {

                Movie = new Movie();
                Movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MOVIE_ID)));
                Movie.setOriginalTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                Movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
//                Movie.setTagline(cursor.getString(cursor.getColumnIndexOrThrow(TAGLINE)));
                Movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                Movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
//                Movie.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(STATUS)));
//                Movie.setOriginalLanguage(cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_LANGUAGE)));
                Movie.setRuntime(cursor.getInt(cursor.getColumnIndexOrThrow(RUNTIME)));
//                Movie.setHomepage(cursor.getString(cursor.getColumnIndexOrThrow(HOMEPAGE)));
                Movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_URL)));
                Movie.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_URL)));

                arrayList.add(Movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Movie Movie) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(MOVIE_ID, Movie.getId());
        initialValues.put(TITLE, Movie.getOriginalTitle());
        initialValues.put(RELEASE_DATE, Movie.getReleaseDate());
//        initialValues.put(TAGLINE, Movie.getTagline());
        initialValues.put(VOTE_AVERAGE, Movie.getVoteAverage());
        initialValues.put(OVERVIEW, Movie.getOverview());
//        initialValues.put(STATUS, Movie.getStatus());
//        initialValues.put(ORIGINAL_LANGUAGE, Movie.getOriginalLanguage());
        initialValues.put(RUNTIME, Movie.getRuntime());
//        initialValues.put(HOMEPAGE, Movie.getHomepage());
        initialValues.put(POSTER_URL, Movie.getPosterPath());
        initialValues.put(BACKDROP_URL, Movie.getBackdropPath());

        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(Movie Movie) {
        ContentValues args = new ContentValues();

        args.put(MOVIE_ID, Movie.getId());
        args.put(TITLE, Movie.getOriginalTitle());
        args.put(RELEASE_DATE, Movie.getReleaseDate());
//        args.put(TAGLINE, Movie.getTagline());
        args.put(VOTE_AVERAGE, Movie.getVoteAverage());
        args.put(OVERVIEW, Movie.getOverview());
//        args.put(STATUS, Movie.getStatus());
//        args.put(ORIGINAL_LANGUAGE, Movie.getOriginalLanguage());
        args.put(RUNTIME, Movie.getRuntime());
//        args.put(HOMEPAGE, Movie.getHomepage());
        args.put(POSTER_URL, Movie.getPosterPath());
        args.put(BACKDROP_URL, Movie.getBackdropPath());

        return database.update(DATABASE_TABLE, args, MOVIE_ID + "= '" + Movie.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(DATABASE_TABLE, MOVIE_ID + " = '" + id + "'", null);
    }

    public Cursor isFavourite(int id) {
        String movie_id = "" + id;
        return database.query(
                DATABASE_TABLE,
                null,
                MOVIE_ID + " = ?",
                new String[]{movie_id},
                null,
                null,
                null
        );
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , MOVIE_ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, MOVIE_ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, MOVIE_ID + " = ?", new String[]{id});
    }

}
