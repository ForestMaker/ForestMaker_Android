package com.example.forestmaker.server

import com.example.forestmaker.server.data.MainResponse
import com.example.forestmaker.server.data.SelectLocationResponse
import com.example.forestmaker.server.data.SignInResponse
import com.example.forestmaker.server.data.SignUpResponse
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

}