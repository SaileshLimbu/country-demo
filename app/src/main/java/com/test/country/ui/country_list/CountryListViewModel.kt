package com.test.country.ui.country_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.country.common.NetworkResult
import com.test.country.domain.model.Country
import com.test.country.domain.use_case.GetCountryUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountryUserCase: GetCountryUserCase
) : ViewModel() {

    private val _countries = MutableLiveData<NetworkResult<List<Country>>>()
    val countries: LiveData<NetworkResult<List<Country>>> get() = _countries

    fun loadCountries() {
        viewModelScope.launch {
            _countries.value = NetworkResult.Loading()
            _countries.value = getCountryUserCase()
        }
    }
}