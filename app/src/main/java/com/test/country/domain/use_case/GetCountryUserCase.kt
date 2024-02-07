package com.test.country.domain.use_case

import com.test.country.common.NetworkResult
import com.test.country.domain.model.Country
import com.test.country.domain.repository.CountryListRepository
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class GetCountryUserCase @Inject constructor(
    private val repo: CountryListRepository
) {
    suspend operator fun invoke(): NetworkResult<List<Country>> {
        return try {
            val response = repo.getCountryList()
            if (response.isEmpty()) {
                NetworkResult.Error("Empty country list.")
            } else {
                NetworkResult.Success(response)
            }
        } catch (e: ConnectException) {
            NetworkResult.Error("Connection error: Check your internet connection.")
        } catch (e: SocketTimeoutException) {
            NetworkResult.Error("Timeout Error: Please try again.")
        } catch (e: IOException) {
            NetworkResult.Error("Please check your internet connection.")
        } catch (e: Exception) {
            NetworkResult.Error("Unexpected error: Please try again.")
        }
    }
}