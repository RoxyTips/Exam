package com.rox.app.movieproject.api;

import com.rox.app.movieproject.pojo.MovieServiceResponse;

/**
 * Created by admuin on 30/10/2016.
 */

public interface IMovieService {
    void getMovies(String sort, String apikey, IRetrofitCallBack<MovieServiceResponse> callBack);
    void getTrailers(int idMovie, String apiKey,IRetrofitCallBack<MovieServiceResponse> callBack);
    void getReviews(int idMovie, String apiKey,IRetrofitCallBack<MovieServiceResponse> callBack);
}
