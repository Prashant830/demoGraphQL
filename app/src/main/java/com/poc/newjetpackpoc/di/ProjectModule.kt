package com.poc.newjetpackpoc.di

import com.poc.newjetpackpoc.projectui.CountryViewModel
import com.poc.newjetpackpoc.networklayer.Repository
import org.koin.dsl.module

val projectModule = module {
    single { Repository() }
    factory { CountryViewModel(get()) }
}