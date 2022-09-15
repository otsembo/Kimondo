package com.otsembo.kimondo.data.repository

import androidx.lifecycle.LiveData
import com.otsembo.kimondo.data.db.dao.ApodDao
import com.otsembo.kimondo.data.model.Apod
import com.otsembo.kimondo.data.network.NasaService

class APODRepository (private val nasaService: NasaService, private val dao: ApodDao) {

    suspend fun fetchFromNetwork(){
        // retrieve from network
        val apodDto = nasaService.getAPOD()
        // convert ApodDTO to Apod
        val apod = Apod(
            date = apodDto.date,
            explanation = apodDto.explanation,
            hdurl = apodDto.hdurl,
            media_type = apodDto.media_type,
            title = apodDto.title
        )
        // save apod to db
        saveApodToDB(apod = apod)
    }

    suspend fun saveApodToDB(apod: Apod){
        dao.insertApod(apod = apod)
    }

    suspend fun clearApods(){
        dao.deleteAllApods()
    }

    fun getLatestApod(): LiveData<Apod> = dao.getLatestApod()

}