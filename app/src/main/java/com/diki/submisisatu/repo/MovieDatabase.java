package com.diki.submisisatu.repo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.diki.submisisatu.Database.DAO;
import com.diki.submisisatu.Model.DataFavoriteMovie;

@Database(entities = {DataFavoriteMovie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract DAO movieDao();
}