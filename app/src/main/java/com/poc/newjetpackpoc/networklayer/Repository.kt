package com.poc.newjetpackpoc.networklayer

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.poc.CountryQuery
import com.poc.newjetpackpoc.networklayer.apimanager.ApiManager
import com.poc.newjetpackpoc.networklayer.apimanager.ApiManager.apolloClient
import com.poc.newjetpackpoc.networklayer.graphqueries.GraphQuries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository() : GraphQuries {

    override suspend fun getCountries(): ApolloResponse<CountryQuery.Data> {
        return apolloClient.query(CountryQuery()).execute()
    }

}


