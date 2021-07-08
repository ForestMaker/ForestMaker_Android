package com.example.forestmaker.server

import com.example.forestmaker.server.data.*
import com.google.gson.JsonObject
import io.reactivex.Observable

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestInterface{

    @POST("/signin")
    fun requestSignIn(@Body body: JsonObject): Call<SignInResponse>

    @POST("/signup")
    fun requestSignUp(@Body body: JsonObject): Call<SignUpResponse>

    @POST("/main")
    fun requestMain(@Body id: JsonObject): Observable<MainResponse>

    @POST("/reserve/location")
    fun reqeustLocation(@Body sgng_nm: String): Call<SelectLocationResponse>

    @POST("/store")
    fun requestStore(@Body type: JsonObject): Call<StoreResponse>
}