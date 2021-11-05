package com.example.waste_segregator.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waste_segregator.models.waw_api_response.WastesResponse
import com.example.waste_segregator.repository.WastesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WastesViewModel @Inject constructor(
    private val repository: WastesRepository
) : ViewModel() {
    val products: MutableLiveData<Response<WastesResponse>> = MutableLiveData()

    fun getWastes(limit: Int) {
        viewModelScope.launch {
            val response = repository.getWastes(1000)
            products.value = response
        }
    }

}