package com.test.country.data.network

import com.test.country.data.dto.CountryDto
import retrofit2.http.GET

interface EndPoints {

    @GET(Constants.GET_COUNTRY_LIST)
    suspend fun getCountryList(): List<CountryDto>
}