package org.catastream.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import org.catastream.R;
import org.catastream.db.sqlLite.WishList;
import org.catastream.model.MovieDetailViewModel;
import org.catastream.ui.activity.main.MainActivity;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailViewModel movieDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String posterUrl = getIntent().getStringExtra("posterUrl");

        TextView titleView = findViewById(R.id.movie_detail_title);
        TextView overviewView = findViewById(R.id.movie_detail_overview);
        ImageView posterView = findViewById(R.id.movie_detail_poster);
        ImageButton homeButton = findViewById(R.id.btn_home);
        ImageButton addToCatalogButton = findViewById(R.id.btn_add_to_catalog);

        titleView.setText(title);
        overviewView.setText(overview);
        Glide.with(this).load(posterUrl).into(posterView);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MovieDetailActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        addToCatalogButton.setOnClickListener(v -> {
            WishList wishList = new WishList();
            wishList.setTitle(title);
            wishList.setOverview(overview);
            wishList.setPosterPath(posterUrl);

            movieDetailViewModel.insertWishList(wishList);

            Toast.makeText(this, "Film ditambahkan ke katalog!", Toast.LENGTH_SHORT).show();
        });
    }
}
