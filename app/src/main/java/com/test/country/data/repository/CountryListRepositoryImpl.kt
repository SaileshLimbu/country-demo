package com.test.country.data.repository

import com.test.country.data.dto.toCountry
import com.test.country.data.network.EndPoints
import com.test.country.domain.repository.CountryListRepository
import javax.inject.Inject

class CountryListRepositoryImpl @Inject constructor(
    private val endPoints: EndPoints
) : CountryListRepository {
    override suspend fun getCountryList() = endPoints.getCountryList().map { it.toCountry() }
}