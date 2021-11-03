package com.example.waste_segregator.api

import com.example.waste_segregator.models.waw_api_response.WastesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WawUmApi {

    @GET("?resource_id=64b9d66c-d134-4a87-9f24-258676e9e498")
    suspend fun getWastes(@Query("limit") limit: Int): Response<WastesResponse>

}