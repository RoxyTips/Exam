package com.rox.app.movieproject.api;

import com.rox.app.movieproject.pojo.MovieServiceResponse;
import com.rox.app.movieproject.pojo.ReviewServiceResponse;
import com.rox.app.movieproject.pojo.TrailerServiceResponse;

/**
 * Created by admuin on 30/10/2016.
 */

public interface IMovieService {
    void getMovies(String sort, String apikey, IRetrofitCallBack<MovieServiceResponse> callBack);
    void getTrailers(int idMovie, String apiKey,IRetrofitCallBack<TrailerServiceResponse> callBack);
    void getReviews(int idMovie, String apiKey, IRetrofitCallBack<ReviewServiceResponse> callBack);
}
