package com.test.country.ui.country_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.country.common.NetworkResult
import com.test.country.data.network.SetRequest
import com.test.country.data.repository.CountryListRepositoryImpl
import com.test.country.domain.model.Country
import com.test.country.domain.use_case.GetCountryUserCase
import kotlinx.coroutines.launch

class CountryListViewModel : ViewModel() {

    private val getCountryListUseCase =
        GetCountryUserCase(CountryListRepositoryImpl(SetRequest.getApiService()))

    private val _countries = MutableLiveData<NetworkResult<List<Country>>>()
    val countries: LiveData<NetworkResult<List<Country>>> get() = _countries

    fun loadCountries() {
        viewModelScope.launch {
            _countries.value = NetworkResult.Loading()
            _countries.value = getCountryListUseCase()
        }
    }
}