package com.forest.forestmaker.server.repository

import com.forest.forestmaker.server.Repository
import com.forest.forestmaker.server.RepositoryImpl
import com.google.gson.JsonObject

class HomeRepository {
    val repository: Repository = RepositoryImpl()

    fun getHome(id: JsonObject) = repository.getHome(id)
}