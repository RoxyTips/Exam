package com.rox.app.movieproject.api;


/**
 * Created by admuin on 30/10/2016.
 */

public interface IRetrofitCallBack<T> {
    void onSuccess(T response);
    void onFailure(String codeError);
}

