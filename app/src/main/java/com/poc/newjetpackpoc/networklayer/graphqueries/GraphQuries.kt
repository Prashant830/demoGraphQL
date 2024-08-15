package com.poc.newjetpackpoc.networklayer.graphqueries

import com.apollographql.apollo.api.ApolloResponse
import com.poc.CountryQuery

interface GraphQuries {

    suspend fun getCountries(): ApolloResponse<CountryQuery.Data>;
}