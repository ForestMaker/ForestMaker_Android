package com.forest.forestmaker.server

import com.forest.forestmaker.server.data.*
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

    @POST("/mypage")
    fun requestMyInfo(@Body id: JsonObject): Call<MyPageResponse>

    @POST("/mypage_edit")
    fun requestEditMyInfo(@Body body: JsonObject): Call<EditMyInfoResponse>

    @POST("/main")
    fun requestMain(@Body id: JsonObject): Observable<MainResponse>

    @POST("/store_tree")
    fun requestStoreTree(): Call<ArrayList<StoreItem>>

    @POST("/store_tonic")
    fun requestStoreTonic(): Call<ArrayList<StoreItem>>

    @POST("/store_rental")
    fun requestStoreRantal(): Call<ArrayList<StoreItem>>

    @POST("/mytrees")
    fun requestMyTree(@Body userid: JsonObject): Call<ArrayList<MyTreeListResponse>>

    @POST("/mytrees_detail")
    fun requestMyTreeDetail(@Body _id: JsonObject): Call<MyTreeListResponse>

    @POST("/mytrees_update")
    fun requestEditMyTree(@Body body: JsonObject): Call<MyTreeListResponse>

    @POST("/findmileage")
    fun requestMileage(@Body id: JsonObject): Call<ArrayList<MileageResponse>>

    @POST("/forestschool")
    fun requestForestSchool(): Call<ArrayList<ForestSchool>>

    @POST("/gongbang")
    fun requestGongbang(): Call<ArrayList<GongBangResponse>>

    @POST("/booking_req")
    fun requestPayment(@Body body: JsonObject): Call<PaymentResponse>

    @POST("/booking_list")
    fun requestReserveInfo(@Body id: JsonObject): Call<ArrayList<ReserveResponse>>

    @POST("/findlocation")
    fun requestTreeLocation(@Body location: JsonObject): Call<ArrayList<FindLocationResponse>>

    @POST("/select_trees")
    fun requestLocationTrees(@Body trees:JsonObject): Call<ArrayList<StoreItem>>
}