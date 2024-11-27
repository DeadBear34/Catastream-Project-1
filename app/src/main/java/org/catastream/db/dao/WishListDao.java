package org.catastream.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.catastream.db.sqlLite.WishList;

import java.util.List;

@Dao
public interface WishListDao {

    @Insert
    void InsertWishList(WishList wishList);

    @Delete
    void DeleteWishList(WishList wishList);

    @Update
    void UpdateWishList(WishList wishList);

    @Query("Select * from WhistList")
    List<WishList> getAllWishList();
}
