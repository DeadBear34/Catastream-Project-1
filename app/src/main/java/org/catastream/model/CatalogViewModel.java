package org.catastream.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.catastream.db.entity.WishList;
import org.catastream.repository.CatalogRepository;

import java.util.List;
/* Jdi class ini tuh buat nampung operasi untuk di class catalog
    perhatiin aja gw udh kasih command
 */

public class CatalogViewModel extends AndroidViewModel {

    private final CatalogRepository repository;
    private final LiveData<List<WishList>> allWishLists;
    /*
    Kalo fungsi dibawah ini kepake cuman di catalog_item
    */
    public CatalogViewModel(Application application) {
        super(application);
        repository = new CatalogRepository(application);
        allWishLists = repository.getAllWishLists();
    }

    public LiveData<List<WishList>> getAllWishLists() {
        return allWishLists;
    }

    public void insert(WishList wishList) {
        repository.insert(wishList);
    }

    public void delete(WishList wishList) {
        repository.delete(wishList);
    }
}
