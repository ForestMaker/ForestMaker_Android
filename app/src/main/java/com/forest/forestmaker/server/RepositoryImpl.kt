package com.forest.forestmaker.server

import com.forest.forestmaker.server.data.MainResponse
import com.google.gson.JsonObject
import io.reactivex.Observable

class RepositoryImpl: Repository {

    val api = RequestToServer.service

    override fun getHome(id: JsonObject): Observable<MainResponse> = api.requestMain(id).map { it }

}