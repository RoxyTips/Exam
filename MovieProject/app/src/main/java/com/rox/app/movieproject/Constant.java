package com.rox.app.movieproject;

import com.rox.app.movieproject.Data.MovieContract;

/**
 * Created by admuin on 30/10/2016.
 */

public class Constant {
    public static String API_KEY = "";
    public static String URL_MOVIE = "http://api.themoviedb.org/3/movie/";
    public static String URL_YOUTUBE = "http://www.youtube.com/watch?v=";
    public static String URL_POSTERS = "http://image.tmdb.org/t/p/w185";
    public static String[] MOVIE_COL = {
            MovieContract.MovieEntry._ID,
            MovieContract.MovieEntry.COLUMN_ID_MOVIE_DB,
            MovieContract.MovieEntry.COLUMN_TITLE,
            MovieContract.MovieEntry.COLUMN_SYNOPSIS,
            MovieContract.MovieEntry.COLUMN_RELEASE_DATE,
            MovieContract.MovieEntry.COLUMN_POSTER,
            MovieContract.MovieEntry.COLUMN_RATE
    };
}
