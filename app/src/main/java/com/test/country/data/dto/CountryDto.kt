package com.test.country.data.dto

import com.test.country.domain.model.Country

data class CountryDto(
    val capital: String,
    val code: String,
    val name: String,
    val region: String
)

fun CountryDto.toCountry() = Country(
    capital = capital,
    code = code,
    name = name,
    region = region
)