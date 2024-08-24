package com.poc.newjetpackpoc.di

import com.poc.newjetpackpoc.projectui.CountryViewModel
import com.poc.newjetpackpoc.networklayer.Repository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val projectModule = module {
    single { Repository() }
    viewModel { CountryViewModel(get()) }
}