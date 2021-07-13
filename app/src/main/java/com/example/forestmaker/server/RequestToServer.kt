package com.example.forestmaker.server

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RequestToServer {

    var BASE_URL = "http://211.208.228.52:9872"
    var test = "http://10.0.2.2:9872/"

//    val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    val clientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor)

    val gson: Gson = GsonBuilder().setLenient().create()

    var retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(clientBuilder.build())
            .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
}