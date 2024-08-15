package com.poc.newjetpackpoc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.poc.newjetpackpoc.networklayer.Repository

class CountryViewModelFactory(
    private val countryRepository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountryViewModel(countryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}