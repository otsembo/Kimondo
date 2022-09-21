package com.otsembo.kimondo.data.network

import com.otsembo.kimondo.data.dto.ApodDTO
import com.otsembo.kimondo.data.dto.MarsRoverPhotos
import com.otsembo.kimondo.data.dto.NasaSearch
import com.otsembo.kimondo.data.dto.NeoWsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {

    @GET("/planetary/apod")
    suspend fun getAPOD(): ApodDTO

    @GET("/neo/rest/v1/feed")
    suspend fun getNeoWs(@Query("start_date") start: String, @Query("end_date") end: String): NeoWsDTO

    // TODO: ADD DATE FUNCTIONALITY
    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsPhotos(@Query("sol") sol: Int =  1000): MarsRoverPhotos

}

interface NasaImageService {

    @GET("/search")
    suspend fun searchNasa(@Query("q") query: String): NasaSearch
}