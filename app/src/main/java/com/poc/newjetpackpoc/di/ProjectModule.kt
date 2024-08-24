package com.poc.newjetpackpoc.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.poc.newjetpackpoc.projectui.CountryViewModel
import com.poc.newjetpackpoc.networklayer.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private const val BASE_URL = "https://countries.trevorblades.com/graphql"
private var okHttp: OkHttpClient? = null


// Client for apollo for graphQL API's
val apolloClient: ApolloClient by lazy {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = (HttpLoggingInterceptor.Level.BODY)

        okHttp = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }


// all classes declaration
val projectModule = module {
    single { apolloClient }
    single { Repository(get()) }
    viewModel { CountryViewModel(get()) }
}