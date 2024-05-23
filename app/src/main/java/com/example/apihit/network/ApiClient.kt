package com.example.apihit.network

import com.example.apihit.common.AppConst
import com.streamo.streamoiptvbox.miscelleneious.common.CustomInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 object ApiClient {
    var httpIterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var httpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpIterceptor)
        .addInterceptor(CustomInterceptor())
        .build()

var retrofit : Retrofit? = null
    var client : Retrofit? = null
        get(){
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(AppConst.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }


}