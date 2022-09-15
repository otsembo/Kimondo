package com.otsembo.kimondo.data.repository

import androidx.lifecycle.LiveData
import com.otsembo.kimondo.data.db.dao.RoverPhotoDao
import com.otsembo.kimondo.data.dto.Rover
import com.otsembo.kimondo.data.model.RoverPhoto
import com.otsembo.kimondo.data.network.NasaService

class MarsPhotosRepository(private val nasaService: NasaService, private val dao: RoverPhotoDao) {

    suspend fun retrieveMarsPhotos(){
        // fetch from network
        val marsPhotoDto = nasaService.getMarsPhotos()
        // loop through every photo
        marsPhotoDto.photos.forEach { photo ->
            val roverPhoto = RoverPhoto(
                id = photo.id.toLong(),
                camera = photo.camera.name,
                earth_date = photo.earth_date,
                img_src = photo.img_src,
                rover = photo.rover.name
            )
            // store rover photos in db
            dao.addRoverPhoto(photo = roverPhoto)
        }
    }

    fun displayPhotos() : LiveData<List<RoverPhoto>> = dao.viewMarsPhotos()

    suspend fun clearPhotos(){
        dao.deleteAllPhotos()
    }

}