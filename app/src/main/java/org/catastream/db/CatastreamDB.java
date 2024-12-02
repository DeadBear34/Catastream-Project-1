package org.catastream.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.catastream.db.dao.WishListDao;
import org.catastream.db.sqlLite.WishList;

@Database(entities = {WishList.class}, version = 1, exportSchema = false)
public abstract class CatastreamDB extends RoomDatabase {

    private static volatile CatastreamDB INSTANCE;

    public abstract WishListDao wishListDao();

    public static CatastreamDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CatastreamDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CatastreamDB.class, "catastream_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
