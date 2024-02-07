package com.test.country.domain.use_case

import com.test.country.common.NetworkResult
import com.test.country.domain.model.Country
import com.test.country.domain.repository.CountryListRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCountryUserCaseTest {

    @Mock
    lateinit var mockRepo: CountryListRepository

    @Test
    fun `invoke should return Success when repo returns non-empty list`() {
        runBlocking {
            val countries = listOf(
                Country("Washington DC", "US", "United States", "NA"),
                Country("London", "UK", "United Kingdom", "CA")
            )
            `when`(mockRepo.getCountryList()).thenReturn(countries)
            val useCase = GetCountryUserCase(mockRepo)

            val result = useCase.invoke()

            assert(result is NetworkResult.Success)
        }
    }

    @Test
    fun `invoke should return Error when repo returns empty list`() {
        runBlocking {
            `when`(mockRepo.getCountryList()).thenReturn(emptyList())
            val useCase = GetCountryUserCase(mockRepo)

            val result = useCase.invoke()

            assert(result is NetworkResult.Error)
        }
    }
}
