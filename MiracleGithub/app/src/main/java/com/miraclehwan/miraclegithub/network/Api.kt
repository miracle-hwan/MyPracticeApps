package com.miraclehwan.miraclegithub.network

import com.miraclehwan.miraclerx.Constants.BASE_URL
import com.miraclehwan.miraclerx.Constants.IS_DEBUG

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Api {

    companion object {
        val RetrofitClient: Api by lazy {
            val httpClient = OkHttpClient.Builder().apply {
                if (IS_DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                }
                connectTimeout(4, TimeUnit.SECONDS)
                readTimeout(4, TimeUnit.SECONDS)
                writeTimeout(4, TimeUnit.SECONDS)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(httpClient)
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
            }.build()

            retrofit.create(Api::class.java)
        }
    }
}