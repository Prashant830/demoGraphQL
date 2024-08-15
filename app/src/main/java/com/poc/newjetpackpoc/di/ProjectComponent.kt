package com.poc.newjetpackpoc.di

import com.poc.newjetpackpoc.projectui.CountryViewModel
import com.poc.newjetpackpoc.networklayer.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ProjectComponents : KoinComponent {
    val repository: Repository by inject()
    val viewModel: CountryViewModel by inject()
}