package com.example.forestmaker.server

import com.example.forestmaker.server.data.MainResponse
import com.example.forestmaker.server.data.StoreResponse
import com.google.gson.JsonObject
import io.reactivex.Observable

interface Repository {
    fun getHome(id: JsonObject) : Observable<MainResponse>
}