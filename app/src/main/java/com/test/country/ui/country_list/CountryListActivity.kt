package com.test.country.ui.country_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.country.R
import com.test.country.common.NetworkResult
import com.test.country.common.hide
import com.test.country.common.show
import com.test.country.databinding.ActivityCountryListBinding
import com.test.country.domain.model.Country
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryListBinding
    private val viewModel by viewModels<CountryListViewModel>()
    private val adapter = CountryListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.tvTitle.text = getString(R.string.title_country_list)

        setupRecyclerView()
        observeCountries()

        binding.refresh.setOnRefreshListener { viewModel.loadCountries() }
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
        binding.recyclerView.show()
        binding.refresh.isRefreshing = false
        adapter.submitList(countryList)
    }

    private fun showLoadingProgress() {
        with(binding) {
            errorView.hide()
            refresh.isRefreshing = true
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        with(binding) {
            errorView.show()
            recyclerView.hide()
            refresh.isRefreshing = false
            errorView.setErrorMessage(errorMessage)
            errorView.setOnRetryListener { viewModel.loadCountries() }
        }
    }
}
