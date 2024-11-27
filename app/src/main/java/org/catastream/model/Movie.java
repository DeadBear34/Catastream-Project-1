package org.catastream.model;

public class Movie {
    private int id;
    private String title;
    private String overview;
    private String poster_path;

    public String getPosterUrl() {
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
