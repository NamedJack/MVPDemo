package com.ultrapower.sc.splash.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.ultrapower.sc.splash.IActivity.ISplashActivity;
import com.ultrapower.sc.splash.modle.Page;
import com.ultrapower.sc.splash.modle.Res;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/19.
 */

public class SplashPresenter {
    private String APIKEY = "34dbade1ebde5a8b13e42e2fe1e4b871";
    private String userName;
    private String passWord;

    private ISplashActivity iSplashActivity;

    public SplashPresenter(ISplashActivity  iSplashActivity) {
        this.iSplashActivity = iSplashActivity;
    }

    public void login(){
        iSplashActivity.showLanding();
        userName = iSplashActivity.getUserName();
        passWord = iSplashActivity.getPassWord();
        if(TextUtils.isEmpty(userName)&&TextUtils.isEmpty(passWord)){
            iSplashActivity.showLanding();
            //联网的操作

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://apis.baidu.com/showapi_open_bus/showapi_joke/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            APIServer server =retrofit.create(APIServer.class);
            Page page = new Page();
            page.page = "1";
            Call<Res> call = server.getJoke("APIKEY","1");
            call.enqueue(new Callback<Res>() {
                @Override
                public void onResponse(Call<Res> call, Response<Res> response) {
                    Res body = response.body();
                    Log.e("笑话",body.toString()+"");

                }

                @Override
                public void onFailure(Call<Res> call, Throwable t) {
                    Log.e("访问异常",t.toString());
                }
            });

        }
    }
//    OkHttpClient client = new  OkHttpClient.Builder().addInterceptor(new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            return null;
//        }
//    }).build();


}
