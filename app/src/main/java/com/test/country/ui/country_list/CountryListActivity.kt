package com.test.country.ui.country_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.country.common.NetworkResult
import com.test.country.common.hide
import com.test.country.common.show
import com.test.country.databinding.ActivityCountryListBinding
import com.test.country.domain.model.Country

class CountryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryListBinding
    private val viewModel by viewModels<CountryListViewModel>()
    private val adapter = CountryListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeCountries()

        viewModel.loadCountries()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeCountries() {
        viewModel.countries.observe(this) { result ->
            when (result) {
                is NetworkResult.Loading -> showLoadingProgress()
                is NetworkResult.Success -> showCountryList(result.data)
                is NetworkResult.Error -> showErrorMessage(result.errorMessage!!)
            }
        }
    }

    private fun showCountryList(countryList: List<Country>?) {
        binding.progressBar.hide()
        adapter.submitList(countryList)
    }

    private fun showLoadingProgress() {
        with(binding) {
            progressBar.show()
            errorView.hide()
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        with(binding) {
            progressBar.hide()
            errorView.show()
            errorView.setErrorMessage(errorMessage)
            errorView.setOnRetryListener { viewModel.loadCountries() }
        }
    }
}
