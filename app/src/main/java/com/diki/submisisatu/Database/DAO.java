package com.diki.submisisatu.Database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.diki.submisisatu.Model.DataFavoriteMovie;

import java.util.List;


@Dao
@Entity
public interface DAO {
    //  Movie

    @Query("SELECT * FROM tMovie")
    LiveData<List<DataFavoriteMovie>> loadAllFavorite();

    @Query("SELECT * FROM tMovie WHERE name = :name")
    List<DataFavoriteMovie> loadAll(String name);

    @Insert
    void insertFavorite(DataFavoriteMovie dataFavoriteMovie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavorite(DataFavoriteMovie dataFavoriteMovie);

    @Delete
    void deleteFavorite(DataFavoriteMovie dataFavoriteMovie);

    @Query("DELETE FROM tMovie WHERE movieid = :movie_id")
    void deleteFavoriteWithId(int movie_id);

    @Query("SELECT * FROM tMovie WHERE id = :id")
    LiveData<DataFavoriteMovie> loadFavoriteById(int id);
}
