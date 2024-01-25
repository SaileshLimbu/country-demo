package com.test.country.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SetRequest {
    private var apiService: ApiService? = null

    fun getApiService(): ApiService {
        return apiService ?: createApiService().also {
            apiService = it
        }
    }

    private fun createApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(EndPoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
