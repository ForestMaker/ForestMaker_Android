package com.forest.forestmaker.server

import com.forest.forestmaker.server.data.MainResponse
import com.google.gson.JsonObject
import io.reactivex.Observable

interface Repository {
    fun getHome(id: JsonObject) : Observable<MainResponse>
}