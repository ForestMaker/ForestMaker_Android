package com.example.forestmaker.server

import com.example.forestmaker.server.data.*
import com.google.gson.JsonObject
import io.reactivex.Observable

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    @POST("/store_tonic")
    fun requestStoreTonic(): Call<ArrayList<StoreItem>>

    @GET("/mytreeList")
    fun requestMyTree(@Query("user") user: String): Call<MyTreeListResponse>

    @GET("/mytreeDetail")
    fun requestMyTreeDetail(@Query("_id") _id: String): Call<MyTreeListResponse>

    @POST("/mytreeDetail/edit")
    fun requestEditMyTree(@Query("_id") _id: String, @Body contents: JsonObject): Call<MyTreeListResponse>

    @POST("/main/mileage")
    fun requestMileage(@Body id: JsonObject): Call<MileageResponse>

    @POST("/forestschool")
    fun requestForestSchool(): Call<ArrayList<ForestSchool>>

    @POST("/gongbang")
    fun requestGongbang(): Call<ArrayList<GongBangResponse>>

}