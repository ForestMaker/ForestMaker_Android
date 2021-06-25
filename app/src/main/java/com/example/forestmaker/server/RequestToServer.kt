package com.example.forestmaker.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestToServer {

    var retrofit = Retrofit.Builder()
        .baseUrl("http://211.208.228.52:9872")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
}