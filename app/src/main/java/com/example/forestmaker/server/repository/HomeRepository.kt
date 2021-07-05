package com.example.forestmaker.server.repository

import com.example.forestmaker.server.Repository
import com.example.forestmaker.server.RepositoryImpl
import com.google.gson.JsonObject

class HomeRepository {
    val repository: Repository = RepositoryImpl()

    fun getHome(id: JsonObject) = repository.getHome(id)
}