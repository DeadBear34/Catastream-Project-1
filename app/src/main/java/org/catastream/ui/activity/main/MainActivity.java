package org.catastream.ui.activity.main;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.catastream.R;
import org.catastream.client.TmdbClientApi;
import org.catastream.client.TmdbClientQuery;
import org.catastream.model.Movie;
import org.catastream.model.MovieList;
import org.catastream.ui.activity.MovieItemActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView trendingRecyclerView;
    private RecyclerView popularRecyclerView;
    private MovieItemActivity trendingAdapter;
    private MovieItemActivity popularAdapter;

    private final String apiKey = "1fcbebcfe0bedc39903831c5a661389c";
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi RecyclerView
        trendingRecyclerView = findViewById(R.id.recycler_view);
        trendingRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        popularRecyclerView = findViewById(R.id.rv_popular_movies);
        popularRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        fetchPopularMovies(currentPage, trendingRecyclerView);
        fetchPopularMovies(currentPage + 1, popularRecyclerView);
    }

    private void fetchPopularMovies(int page, RecyclerView recyclerView) {
        TmdbClientApi.getRetrofitInstance().create(TmdbClientQuery.class)
                .getPopularMovies(apiKey, "en-US", page)
                .enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Movie> movies = response.body().getResults();
                            MovieItemActivity adapter = new MovieItemActivity(MainActivity.this, movies);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {
                        Log.e("API Error", t.getMessage());
                    }
                });
    }
}
