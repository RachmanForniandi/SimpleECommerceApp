package com.example.simpleecommerceapp.network

import com.example.simpleecommerceapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {

    fun getInterceptor():OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client:OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return client
    }

    fun useRetrofit():Retrofit{

        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit
    }

    fun useService():APIService = useRetrofit().create(APIService::class.java)
}