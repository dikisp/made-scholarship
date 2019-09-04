package com.diki.myprofile.Model;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.diki.myprofile.DAO;


/**
 * 3 Agustus 2019
 * 10116352
 * Diki Supriadi
 * IF-8
 */

@Database(entities =  {FriendData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO dao();
}

