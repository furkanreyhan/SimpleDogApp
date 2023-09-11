package com.furkanreyhan.dogapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanreyhan.dogapp.model.DogResponse
import com.furkanreyhan.dogapp.network.RemoteService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomDogViewModel : ViewModel() {
    private val _dogImageUrl = MutableLiveData<String>()
    val dogImageUrl: LiveData<String> = _dogImageUrl

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getRandomDogImage() {
        _isLoading.value = true

        RemoteService.dogApi.getDog().enqueue(object : Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                if (response.isSuccessful && response.body()!=null ) {
                    val dogResponse = response.body()
                    val imageUrl = dogResponse?.message

                    imageUrl?.let {
                        _dogImageUrl.value = it
                    }
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }
}