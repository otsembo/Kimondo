package com.otsembo.kimondo.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {

    private const val BASE_URL = "https://api.nasa.gov/"
    private const val NASA_IMAGES_BASE_URL = "https://images-api.nasa.gov/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun setUpApiKeyInterceptor(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter("api_key","7fLcGLQjB7M69kdvqt7JsAsIMqfQmjUmzNujXFVM").build())
            .build()
    )

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder()
            .readTimeout(5000L, TimeUnit.MILLISECONDS)
            .addInterceptor { block -> setUpApiKeyInterceptor(block) }.build())
        .build()


    private val nasaImageRetrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(NASA_IMAGES_BASE_URL)
        .client(OkHttpClient.Builder()
            .readTimeout(5000L, TimeUnit.MILLISECONDS)
            .build())
        .build()


    val nasaService: NasaService = retrofit.create(NasaService::class.java)
    val nasaImageService: NasaImageService = nasaImageRetrofit.create(NasaImageService::class.java)

}