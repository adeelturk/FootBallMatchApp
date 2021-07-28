package com.turk.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

val networkModule = module {
    single { OkHttpClient.Builder() }


    single<Retrofit> {
        val logger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }


        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.apply {

            baseUrl(BuildConfig.END_POINT)
            addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())

        }.also {
            val okHttpClientBuilder = get<OkHttpClient.Builder>()
            okHttpClientBuilder.addInterceptor(logger)
            it.client(okHttpClientBuilder.build())
            okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
            okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS).build()
        }
        retrofitBuilder.build()

    }


}