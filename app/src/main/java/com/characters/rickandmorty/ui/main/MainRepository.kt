package com.characters.rickandmorty.ui.main

import com.characters.rickandmorty.network.ApiService
import com.characters.rickandmorty.network.BaseRepository
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService): BaseRepository() {

    suspend fun getCharacters() = safeApiRequest {
        apiService.getCharacters()
    }

}