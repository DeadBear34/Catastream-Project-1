package org.catastream.db;

import androidx.room.Database;

import org.catastream.db.sqlLite.WishList;

@Database(entities = {WishList.class}, version = 1, exportSchema = false)
public abstract class CatastreamDB {
}
