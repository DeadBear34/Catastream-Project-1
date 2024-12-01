package org.catastream.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.catastream.R;
import org.catastream.db.entity.WishList;
import org.catastream.model.CatalogViewModel;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {

    private CatalogViewModel catalogViewModel;
    private CatalogAdapter catalogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Inisialisasi RecyclerView dan Adapter
        RecyclerView catalogRecyclerView = findViewById(R.id.catalog_recycler_view);
        catalogRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adapter dengan listener untuk tombol delete
        catalogAdapter = new CatalogAdapter(new ArrayList<>(), wishList -> {
            // Panggil ViewModel untuk menghapus data
            catalogViewModel.delete(wishList);
        });
        catalogRecyclerView.setAdapter(catalogAdapter);

        // Inisialisasi ViewModel dan observer data
        catalogViewModel = new ViewModelProvider(this).get(CatalogViewModel.class);

        // Observe LiveData untuk pembaruan data katalog
        catalogViewModel.getAllWishLists().observe(this, wishLists -> catalogAdapter.updateWishLists(wishLists));
    }
}
