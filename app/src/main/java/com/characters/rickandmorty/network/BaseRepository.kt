package com.characters.rickandmorty.network

import android.util.Log
import com.characters.rickandmorty.R
import com.characters.rickandmorty.RickAndMortyApplication.Companion.getAppContext
import com.characters.rickandmorty.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    private val maxRetryCount = 3
    private val retryInterval = 1000 // milliseconds
    var retryCount = 0

    suspend fun <T> safeApiRequest(
        apiRequest: suspend () -> T
    ): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                val result = apiRequest.invoke()
                retryCount = 0
                Resource.Success(result)
            } catch (throwable: Throwable) {


                if (retryCount < maxRetryCount) {

                    retryCount++
                    delay(retryInterval.toLong())
                    safeApiRequest(apiRequest) // Tekrar deneme

                } else {
                    retryCount = 0
                    when (throwable) {

                        is HttpException -> {
                            Resource.Error(
                                false, throwable.response()?.errorBody()?.string()
                            )
                        }
                        else -> Resource.Error(true, throwable.localizedMessage)
                    }

                }


            }
        }
    }

}


