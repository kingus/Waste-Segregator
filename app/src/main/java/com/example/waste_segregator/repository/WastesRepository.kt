package com.example.waste_segregator.repository

import com.example.waste_segregator.api.RetrofitInstance
import com.example.waste_segregator.models.waw_api_response.WastesResponse
import retrofit2.Response

class WastesRepository {
    suspend fun getWastes(limit: Int): Response<WastesResponse> = RetrofitInstance.api.getWastes(limit)
}