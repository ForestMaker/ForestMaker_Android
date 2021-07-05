package com.example.forestmaker.server

import com.example.forestmaker.server.data.MainResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Callback

class RepositoryImpl: Repository {

    val api = RequestToServer.service

    override fun getHome(id: JsonObject): Observable<MainResponse> = api.requestMain(id).map { it }

}