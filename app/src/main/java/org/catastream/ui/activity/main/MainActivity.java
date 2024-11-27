package org.catastream.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.catastream.model.Movie;
import org.catastream.R;
import org.catastream.client.TmdbClientApi;
import org.catastream.ui.activity.MovieItemActivity;
import org.catastream.client.TmdbClientQuery;
import org.catastream.model.MovieList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieItemActivity movieItemActivity;

    private final String apiKey = "1fcbebcfe0bedc39903831c5a661389c";
    private int currentPage = 1;

    private void fetchPopularMovies(int page) {
        TmdbClientApi.getRetrofitInstance().create(TmdbClientQuery.class)
                .getPopularMovies(apiKey, "en-US", page)
                .enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Movie> movies = response.body().getResults();
                            movieItemActivity = new MovieItemActivity(MainActivity.this, movies);
                            recyclerView.setAdapter(movieItemActivity);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {
                        Log.e("API Error", t.getMessage());
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        fetchPopularMovies(currentPage);

    }
}
