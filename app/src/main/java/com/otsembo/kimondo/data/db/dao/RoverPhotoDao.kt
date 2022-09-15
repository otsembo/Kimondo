package com.otsembo.kimondo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otsembo.kimondo.data.model.RoverPhoto

@Dao
interface RoverPhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRoverPhoto(photo: RoverPhoto)

    @Update
    suspend fun updateRoverPhoto(photo: RoverPhoto)

    @Delete
    suspend fun deleteRoverPhoto(photo: RoverPhoto)

    @Query("SELECT * FROM mars_photos")
    fun viewMarsPhotos(): LiveData<List<RoverPhoto>>

    @Query("DELETE from mars_photos")
    suspend fun deleteAllPhotos()

}