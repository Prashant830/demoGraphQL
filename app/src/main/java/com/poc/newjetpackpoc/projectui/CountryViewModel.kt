package com.poc.newjetpackpoc.projectui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.CountryQuery
import com.poc.newjetpackpoc.networklayer.Repository
import kotlinx.coroutines.launch

class CountryViewModel(private val countryRepository: Repository) : ViewModel() {

    private val _countries = MutableLiveData<List<CountryQuery.Country>>()
    val countries: LiveData<List<CountryQuery.Country>> = _countries

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                val response = countryRepository.getCountries()
                val countryList = response.data?.countries ?: emptyList()
                _countries.value = countryList
            } catch (e: Exception) {
                _countries.value = emptyList() // Handle the error by setting an empty list
            }
        }
    }
}
