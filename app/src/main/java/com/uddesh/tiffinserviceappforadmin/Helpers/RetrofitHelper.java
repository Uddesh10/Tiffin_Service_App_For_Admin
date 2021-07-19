package com.uddesh.tiffinserviceappforadmin.Helpers;

import com.uddesh.tiffinserviceappforadmin.Repository.ApiCalls;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.42.97:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ApiCalls apiCalls = retrofit.create(ApiCalls.class);

}
