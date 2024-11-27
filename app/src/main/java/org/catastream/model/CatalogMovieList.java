package org.catastream.model;

import java.util.ArrayList;
import java.util.List;

public class CatalogMovieList {
    private static List<Movie> catalogMovie;

    public static List<Movie> getMovieList() {
        return catalogMovie;
    }

    public static void setMovieList(List<Movie> movieList) {
        CatalogMovieList.catalogMovie = movieList;
    }
}
