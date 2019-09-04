package com.diki.submisisatu.Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import retrofit2.http.Query;

@Dao
@Entity
public interface DAO {
    //  Friend
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFriend(FriendData friend);

    @Query("SELECT * FROM tFriend")
    FriendData[] selectAllFriend();

    @Query("SELECT * FROM tFriend WHERE nim = :nim LIMIT 1")
    FriendData selectFriend(String nim);

    @Update
    int updateFriend(FriendData friend);

    @Delete
    int deleteFriend(FriendData friend);
}
