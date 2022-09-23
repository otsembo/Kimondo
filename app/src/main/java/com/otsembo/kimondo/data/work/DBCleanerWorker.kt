package com.otsembo.kimondo.data.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.otsembo.kimondo.data.db.AppDatabase
import com.otsembo.kimondo.data.network.RetrofitUtils
import com.otsembo.kimondo.data.repository.APODRepository
import com.otsembo.kimondo.data.repository.MarsPhotosRepository
import com.otsembo.kimondo.data.repository.NEOWSRepository
import com.otsembo.kimondo.data.repository.SearchRepository

class DBCleanerWorker(applicationContext: Context, workerParameters: WorkerParameters)
    : CoroutineWorker(applicationContext, workerParameters){

    override suspend fun doWork(): Result {
        return try {
            clearDB()
            Result.success()
        }catch (e: Exception){
            Result.retry()
            Result.failure()
        }

    }

    private suspend fun clearDB(){
        val DB = AppDatabase.getDB(applicationContext)
        val nasaService = RetrofitUtils.nasaService
        val nasaImageService = RetrofitUtils.nasaImageService

        val apodRepo = APODRepository(
            nasaService, DB.apodDao()
        )
        // clear apod
        apodRepo.clearApods()

        val marsPhotoRepo = MarsPhotosRepository(
            nasaService, DB.roverPhotoDao()
        )
        // clear mars rover photos
        marsPhotoRepo.clearPhotos()

        val neoRepo = NEOWSRepository(
            DB.neoDao(), nasaService
        )
        // clear neos
        neoRepo.clearNeoWs()

        val searchRepo = SearchRepository(
            searchDataDao = DB.searchDataDao(),
            albumDao = DB.albumDao(),
            keywordsDao = DB.keywordDao(),
            nasaImageService = nasaImageService
        )
        // clear search history
        searchRepo.clearSearchHistory()
    }

}