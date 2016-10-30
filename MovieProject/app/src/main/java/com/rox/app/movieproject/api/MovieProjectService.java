package com.rox.app.movieproject.api;

import com.rox.app.movieproject.Constant;
import com.rox.app.movieproject.pojo.MovieServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by admuin on 30/10/2016.
 */

public class MovieProjectService implements IMovieService {

    private IMovieServiceAPI serviceAPI;

    public MovieProjectService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_MOVIE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceAPI = retrofit.create(IMovieServiceAPI.class);
    }

    @Override
    public void getMovies(String sort, String apikey, final IRetrofitCallBack<MovieServiceResponse> callBack) {

        Call<MovieServiceResponse> call = serviceAPI.getMovies(sort, apikey);
        call.enqueue(new Callback<MovieServiceResponse>() {
            @Override
            public void onResponse(Call<MovieServiceResponse> call, Response<MovieServiceResponse> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailure("failure in response / code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieServiceResponse> call, Throwable t) {
                callBack.onFailure("service failure : " + t.getMessage());
            }
        });
    }

    @Override
    public void getTrailers(int idMovie, String apiKey, final IRetrofitCallBack<MovieServiceResponse> callBack) {
        Call<MovieServiceResponse> call = serviceAPI.getTrailers(idMovie, apiKey);
        call.enqueue(new Callback<MovieServiceResponse>() {
            @Override
            public void onResponse(Call<MovieServiceResponse> call, Response<MovieServiceResponse> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailure("failure in response / code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieServiceResponse> call, Throwable t) {
                callBack.onFailure("service failure : " + t.getMessage());
            }
        });
    }

    @Override
    public void getReviews(int idMovie, String apiKey, final IRetrofitCallBack<MovieServiceResponse> callBack) {
        Call<MovieServiceResponse> call = serviceAPI.getReviews(idMovie, apiKey);
        call.enqueue(new Callback<MovieServiceResponse>() {
            @Override
            public void onResponse(Call<MovieServiceResponse> call, Response<MovieServiceResponse> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailure("failure in response / code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieServiceResponse> call, Throwable t) {
                callBack.onFailure("service failure : " + t.getMessage());
            }
        });
    }
}
