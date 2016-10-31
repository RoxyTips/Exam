package com.rox.app.movieproject.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admuin on 30/10/2016.
 */

public class MovieServiceResponse implements Serializable{

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("results")
    @Expose
    public List<Movie> movies = new ArrayList<>();
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;

    public class Movie implements Serializable {
        public Movie(String posterPath, String overview, String releaseDate, Float voteAverage, String title, Integer id){
            this.id = id;
            this.overview = overview;
            this.posterPath = posterPath;
            this.releaseDate = releaseDate;
            this.title = title;
            this.voteAverage = voteAverage;
        }
        @SerializedName("poster_path")
        @Expose
        public String posterPath;
        @SerializedName("adult")
        @Expose
        public Boolean adult;
        @SerializedName("overview")
        @Expose
        public String overview;
        @SerializedName("release_date")
        @Expose
        public String releaseDate;
        @SerializedName("genre_ids")
        @Expose
        public List<Integer> genreIds = new ArrayList<Integer>();
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("original_title")
        @Expose
        public String originalTitle;
        @SerializedName("original_language")
        @Expose
        public String originalLanguage;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("backdrop_path")
        @Expose
        public String backdropPath;
        @SerializedName("popularity")
        @Expose
        public Float popularity;
        @SerializedName("vote_count")
        @Expose
        public Integer voteCount;
        @SerializedName("video")
        @Expose
        public Boolean video;
        @SerializedName("vote_average")
        @Expose
        public Float voteAverage;


    }
}
