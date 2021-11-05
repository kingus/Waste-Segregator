package com.example.waste_segregator.repository

import com.example.waste_segregator.api.WawUmApi
import com.example.waste_segregator.models.waw_api_response.WastesResponse
import retrofit2.Response
import javax.inject.Inject

class WastesRepository @Inject constructor(
    private val api: WawUmApi
) {
    suspend fun getWastes(limit: Int): Response<WastesResponse> =
        api.getWastes(limit)
}