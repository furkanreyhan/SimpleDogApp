package com.furkanreyhan.dogapp.network

import com.furkanreyhan.dogapp.model.DogResponse
import retrofit2.Call
import retrofit2.http.GET

interface DogApi {
    @GET("breeds/image/random")
    fun getDog(): Call<DogResponse>
}