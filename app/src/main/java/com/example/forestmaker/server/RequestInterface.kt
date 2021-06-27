package com.example.forestmaker.server

import com.example.forestmaker.data.MyTreeData
import com.example.forestmaker.server.data.MainResponse
import com.example.forestmaker.server.data.SignInResponse
import com.example.forestmaker.server.data.SignUpResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestInterface{

    @POST("/signin")
    fun requestSignIn(@Body body: JsonObject): Call<SignInResponse>

    @POST("/signup")
    fun requestSignUp(@Body body: JsonObject): Call<SignUpResponse>

    @POST("/main")
    fun requestMain(@Body id: String): Call<MainResponse>

    @POST("/mytree")
    fun requestMyTree(@Body id: String): Call<MutableList<MyTreeData>>

}