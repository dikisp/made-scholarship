package com.diki.submisisatu.repo;

import android.arch.persistence.room.Room;
import android.content.Context;

public class MovieRepository {
    private String DB_NAME = "db_profileApp";
    private MovieDatabase database;
    private Context context;

    private MovieRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, MovieDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    private static MovieRepository INSTANCE = null;

    public static MovieRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository(context);
        }

        return INSTANCE;
    }
}
