package com.diki.myprofile;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.diki.myprofile.Model.Friend;
import com.diki.myprofile.Model.FriendData;

/**
 * 14 Agustus 2019
 * 10116352
 * Diki Supriadi
 * IF-8
 */
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
