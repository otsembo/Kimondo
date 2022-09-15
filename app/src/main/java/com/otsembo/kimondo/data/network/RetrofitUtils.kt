package com.otsembo.kimondo.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitUtils {

    private const val BASE_URL = "https://api.nasa.gov/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun setUpApiKeyInterceptor(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter("api_key","DEMO_KEY").build())
            .build()
    )

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().addInterceptor { block -> setUpApiKeyInterceptor(block) }.build())
        .build()

    val nasaService: NasaService = retrofit.create(NasaService::class.java)

}