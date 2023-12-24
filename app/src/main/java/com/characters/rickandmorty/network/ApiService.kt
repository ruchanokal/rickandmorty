package com.characters.rickandmorty.network

import com.characters.rickandmorty.model.CloudResponse
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters() : CloudResponse

}