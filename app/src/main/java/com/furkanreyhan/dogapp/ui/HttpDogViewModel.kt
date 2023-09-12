package com.furkanreyhan.dogapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HttpDogViewModel : ViewModel() {
    private val _selectedDogImageUrl = MutableLiveData<String>()
    val selectedDogImageUrl: LiveData<String> = _selectedDogImageUrl
    fun setSelectedDogImageUrl(imageUrl: String) {
        _selectedDogImageUrl.value = "https://http.dog/$imageUrl.jpg"
    }
}