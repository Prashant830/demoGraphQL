package com.poc.newjetpackpoc.networklayer

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.poc.CountryQuery
import com.poc.newjetpackpoc.networklayer.retrofit.EndpointQueries
import com.poc.newjetpackpoc.networklayer.graphqueries.GraphQuries
import com.poc.newjetpackpoc.networklayer.retrofit.model.RetrofitModels
import retrofit2.Response
import retrofit2.Retrofit

class Repository(private var apolloClient: ApolloClient , private var retrofit: Retrofit) : GraphQuries {

    override suspend fun getCountries(): ApolloResponse<CountryQuery.Data> {
        return apolloClient.query(CountryQuery()).execute()
    }


    suspend fun getPostResponse(): Response<RetrofitModels> {
        return retrofit.create(EndpointQueries::class.java).getPostResponse()
    }

}


