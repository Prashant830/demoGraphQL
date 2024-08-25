package com.poc.newjetpackpoc.networklayer.retrofit

import com.poc.newjetpackpoc.networklayer.retrofit.model.RetrofitModels
import retrofit2.Response
import retrofit2.http.GET

interface EndpointQueries {

    @GET("/posts")
    suspend fun getPostResponse() : Response<RetrofitModels>;
}