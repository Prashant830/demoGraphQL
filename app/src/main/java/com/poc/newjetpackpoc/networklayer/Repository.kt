package com.poc.newjetpackpoc.networklayer

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.poc.CountryQuery
import com.poc.newjetpackpoc.networklayer.graphqueries.GraphQuries

class Repository(private var apolloClient: ApolloClient) : GraphQuries {

    override suspend fun getCountries(): ApolloResponse<CountryQuery.Data> {
        return apolloClient.query(CountryQuery()).execute()
    }

}


