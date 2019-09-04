package com.diki.submisisatu.Database;

@Database(entities =  {FriendData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO dao();
}
