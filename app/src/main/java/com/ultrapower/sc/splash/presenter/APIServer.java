package com.ultrapower.sc.splash.presenter;

import com.ultrapower.sc.splash.modle.Res;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/12/21
 */

public interface APIServer {
    @FormUrlEncoded
    @POST("joke_text")
    Call<Res> getJoke(@Header("apikey") String key, @Field("page") String page);
}
