package com.example.waste_segregator.di

import com.example.waste_segregator.api.WawUmApi
import com.example.waste_segregator.repository.WastesRepository
import com.example.waste_segregator.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWawUmApi(): WawUmApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_UM_WARSZAWA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WawUmApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: WawUmApi): WastesRepository {
        return WastesRepository(api)
    }
}