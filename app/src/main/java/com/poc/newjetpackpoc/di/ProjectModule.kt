package com.poc.newjetpackpoc.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.http.LoggingInterceptor
import com.apollographql.apollo.network.okHttpClient
import com.poc.newjetpackpoc.projectui.CountryViewModel
import com.poc.newjetpackpoc.networklayer.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sin

private const val BASE_URL = "https://countries.trevorblades.com/graphql"
private const val BASE_URL_TWO = "https://jsonplaceholder.typicode.com"

private var okHttp: OkHttpClient? = null


// login interceptor declaration
fun loginInterceptor() : HttpLoggingInterceptor{
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = (HttpLoggingInterceptor.Level.BODY)

    okHttp = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return loggingInterceptor
}


// okhttpClient declaration
fun okhttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}


// Client for apollo for graphQL API's
fun apolloClient(okHttpClient: OkHttpClient): ApolloClient {

       return ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }


// Client for Retrofit for have EndPoint API's
fun retrofitClient(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl(BASE_URL_TWO)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


// all classes declaration
val projectModule = module {
    single { loginInterceptor() }
    single { okhttpClient(get()) }

    // here I created client for api's, injected in loginInterceptor so didn't need to add @inject
    single { apolloClient(get()) }
    single { retrofitClient(get()) }

    // here apolloClient is injected in repository so didn't need to add @inject
    single { Repository(get(), get()) }

    // same with viewmodel is injected in CountryViewModel so didn't need to add @inject
    viewModel { CountryViewModel(get()) }
}