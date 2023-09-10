package com.furkanreyhan.dogapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteService {
    private val BASE_URL = "https://dog.ceo/api/"

    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit {

        return retrofit
            ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .apply {
                    retrofit = this
                }
    }

    val dogApi: DogApi = getRetrofit().create(DogApi::class.java)
}