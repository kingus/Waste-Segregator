package com.example.waste_segregator.api

import com.example.waste_segregator.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.API_UM_WARSZAWA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: WawUmApi by lazy {
        retrofit.create(WawUmApi::class.java)
    }
}