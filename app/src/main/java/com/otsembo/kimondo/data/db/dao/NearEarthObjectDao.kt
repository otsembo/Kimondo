package com.otsembo.kimondo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otsembo.kimondo.data.model.NearEarthObject
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NearEarthObjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNEO(nearEarthObject: NearEarthObject)

    @Update
    suspend fun updateNEO(nearEarthObject: NearEarthObject)

    @Delete
    suspend fun deleteNEO(nearEarthObject: NearEarthObject)

    @Query("SELECT * FROM near_earth_objects")
    fun getNEOs(): LiveData<List<NearEarthObject>>

    @Query("DELETE FROM near_earth_objects")
    suspend fun clearNeoWs()

}