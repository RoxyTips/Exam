package com.rox.app.movieproject.api;

import com.rox.app.movieproject.pojo.MovieServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admuin on 30/10/2016.
 */

public interface IMovieServiceAPI {

    @GET("{sort}")
    Call<MovieServiceResponse> getMovies(@Path("sort") String sort, @Query("api_key") String apiKey);

    @GET("{id}")
    Call<MovieServiceResponse> getTrailers(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("{id}")
    Call<MovieServiceResponse> getReviews(@Path("id") int id, @Query("api_key") String apiKey);
}
