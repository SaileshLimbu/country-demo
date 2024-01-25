package com.test.country.data.repository

import com.test.country.data.dto.toCountry
import com.test.country.data.network.ApiService
import com.test.country.domain.repository.CountryListRepository

class CountryListRepositoryImpl(
    private val apiService: ApiService
) : CountryListRepository {
    override suspend fun getCountryList() = apiService.getCountryList().map { it.toCountry() }
}