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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFriend(DataFavoriteMovie friend);

    @Query("SELECT * FROM tMovie")
    DataFavoriteMovie[] selectAllMovie();

    @Query("SELECT * FROM tMovie WHERE id = :id LIMIT 1")
    DataFavoriteMovie selectFriend(String id);

    @Update
    int updateFriend(DataFavoriteMovie friend);

    @Delete
    int deleteFriend(DataFavoriteMovie friend);

    @Query("SELECT * FROM tMovie")
    LiveData<List<DataFavoriteMovie>> loadAllFavorite();

    @Query("SELECT * FROM tMovie WHERE OriginalTitle = :title")
    List<DataFavoriteMovie> loadAll(String title);

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
