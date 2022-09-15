package com.otsembo.kimondo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otsembo.kimondo.data.model.Apod

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apod: Apod)

    @Delete
    suspend fun deleteApod(apod: Apod)

    @Update
    suspend fun updateApod(apod: Apod)

    @Query("SELECT * FROM astronomy_picture_of_day ORDER BY id DESC LIMIT 1")
    fun getLatestApod() : LiveData<Apod>

    @Query("DELETE FROM astronomy_picture_of_day")
    suspend fun deleteAllApods()

}