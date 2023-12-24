package com.characters.rickandmorty.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.characters.rickandmorty.model.CloudResponse
import com.characters.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MainRepository) : ViewModel() {

    private var _cloudResponse = MutableLiveData<CloudResponse?>()
    var cloudResponse : LiveData<CloudResponse?> = _cloudResponse

    private var _errorMessage = MutableLiveData<String?>()
    var errorMessage : LiveData<String?> = _errorMessage

    private var _isLoading = MutableLiveData<Boolean>(true)
    var isLoading : LiveData<Boolean> = _isLoading

    fun getData() = viewModelScope.launch {

        _isLoading.value = true
        val request = movieRepository.getCharacters()

        when(request){
            is Resource.Success -> {

                Log.e("MainViewModel","data-1: " + request.data)
                _cloudResponse.value = request.data
                _isLoading.value = false
            }

            is Resource.Error -> {
                _errorMessage.value = request.message
                _isLoading.value = false
            }

            else ->{
                _isLoading.value = false
            }

        }
    }
}