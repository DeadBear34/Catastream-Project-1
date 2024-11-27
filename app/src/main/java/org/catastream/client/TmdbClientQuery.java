package org.catastream.client;

import org.catastream.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbClientQuery {

    @GET("movie/popular")
    Call<MovieList> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/list")
    Call<MovieList> getMovieList(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}

