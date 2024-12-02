package org.catastream.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import org.catastream.db.sqlLite.WishList;

import java.util.List;

@Dao
public interface WishListDao {
    @Insert
    void insertWishList(WishList wishList);

    @Delete
    void deleteWishList(WishList wishList);

    @Query("SELECT * FROM WishList")
    LiveData<List<WishList>> getAllWishLists();
}
