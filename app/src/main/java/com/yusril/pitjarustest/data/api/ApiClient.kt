package com.yusril.pitjarustest.data.api

import com.yusril.pitjarustest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE
        }).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://keraton.indward.com/api/sariroti_md/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API_SERVICE: ApiServices = getRetrofit().create(ApiServices::class.java)
}