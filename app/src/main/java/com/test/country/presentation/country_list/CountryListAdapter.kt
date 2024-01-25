package com.test.country.presentation.country_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.country.databinding.ItemCountryBinding
import com.test.country.domain.model.Country

class CountryListAdapter : ListAdapter<Country, CountryListAdapter.Holder>(MyDiffUtils()) {

    class Holder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val country = getItem(position)
        holder.binding.tvNameRegion.text = "${country.name}, ${country.region}"
        holder.binding.tvCode.text = country.code
        holder.binding.tvCapital.text = country.capital
    }

    class MyDiffUtils : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}