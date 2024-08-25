package com.poc.newjetpackpoc.networklayer.graphqueries

import com.poc.newjetpackpoc.networklayer.graphqueries.model.RetrofitModels
import retrofit2.Response
import retrofit2.http.GET

interface EndpointQueries {

    @GET("/posts")
    suspend fun getPostResponse() : Response<RetrofitModels>;
}