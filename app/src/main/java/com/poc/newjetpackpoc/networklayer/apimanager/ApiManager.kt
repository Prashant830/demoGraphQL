package com.poc.newjetpackpoc.networklayer.apimanager

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object ApiManager {

    private const val BASE_URL = "https://countries.trevorblades.com/graphql"
    private var okHttp: OkHttpClient? = null

    val apolloClient: ApolloClient by lazy {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = (HttpLoggingInterceptor.Level.BODY)

        okHttp = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        // Create an OkHttpClient instance with the logging interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }
}