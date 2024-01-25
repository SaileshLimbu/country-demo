package com.test.country.domain.repository

import com.test.country.domain.model.Country

interface CountryListRepository {
    suspend fun getCountryList(): List<Country>
}