package com.diki.submisisatu.Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.diki.submisisatu.Model.DataFavoriteMovie;


@Dao
@Entity
public interface DAO {
    //  Friend
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
}
