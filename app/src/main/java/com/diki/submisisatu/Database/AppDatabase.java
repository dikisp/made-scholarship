package com.diki.submisisatu.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.diki.submisisatu.Model.DataFavoriteMovie;

@Database(entities =  {DataFavoriteMovie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO dao();
}
