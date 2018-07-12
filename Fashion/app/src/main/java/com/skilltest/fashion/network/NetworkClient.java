package com.skilltest.fashion.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    public static final String BASE_URL  = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/";
    public static final String ModelList_URL = "master.json";

    public static Retrofit mRetrofit = null;

    public static Retrofit getInstance(){
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  mRetrofit;
    }
}
