package org.catastream.model;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.catastream.db.sqlLite.CatalogRepository;
import org.catastream.db.sqlLite.WishList;


/* Jdi class ini tuh buat nampung operasi untuk di class catalog
    perhatiin aja gw udh kasih command
 */

public class MovieDetailViewModel extends AndroidViewModel {

    private final CatalogRepository repository;

    public MovieDetailViewModel(Application application) {
        super(application);
        repository = new CatalogRepository(application);
    }
    /*
    Kalo fungsi dibawah ini kepake cuman di detail movie
    */
    public void insertWishList(WishList wishList) {
        repository.insert(wishList);
    }

}
