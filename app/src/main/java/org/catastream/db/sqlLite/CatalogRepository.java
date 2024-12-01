package org.catastream.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.catastream.db.CatastreamDB;
import org.catastream.db.dao.WishListDao;
import org.catastream.db.entity.WishList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CatalogRepository {

    private final WishListDao wishListDao;
    private final LiveData<List<WishList>> allWishLists;
    private final ExecutorService executorService;

    public CatalogRepository(Application application) {
        CatastreamDB db = CatastreamDB.getInstance(application);
        wishListDao = db.wishListDao();
        allWishLists = wishListDao.getAllWishLists();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<WishList>> getAllWishLists() {
        return allWishLists;
    }

    public void insert(WishList wishList) {
        executorService.execute(() -> wishListDao.insertWishList(wishList));
    }

    public void delete(WishList wishList) {
        executorService.execute(() -> wishListDao.deleteWishList(wishList));
    }
}
