package com.otsembo.kimondo.data.repository

import androidx.lifecycle.LiveData
import com.otsembo.kimondo.data.db.AppDatabase
import com.otsembo.kimondo.data.db.dao.NearEarthObjectDao
import com.otsembo.kimondo.data.model.NearEarthObject
import com.otsembo.kimondo.data.network.NasaService

class NEOWSRepository(private val dao: NearEarthObjectDao, private val nasaService: NasaService) {

    suspend fun retrieveNearEarthObjects(start_date: String, end_date: String){
        val objects = nasaService.getNeoWs(start = start_date, end = end_date)

        objects.near_earth_objects.values.forEach { neos ->
            neos.forEach {  earthObjectData ->
                val nearEarth = NearEarthObject(
                    id = earthObjectData.id.toLong(),
                    absolute_magnitude_h = earthObjectData.absolute_magnitude_h,
                    estimated_diameter_max = earthObjectData.estimated_diameter.kilometers.estimated_diameter_max,
                    estimated_diameter_min = earthObjectData.estimated_diameter.kilometers.estimated_diameter_min,
                    is_potentially_hazardous_asteroid = earthObjectData.is_potentially_hazardous_asteroid,
                    name = earthObjectData.name,
                    nasa_jpl_url = earthObjectData.nasa_jpl_url
                )
                // store in db
                dao.addNEO(nearEarthObject = nearEarth)
            }
        }
    }

    suspend fun clearNeoWs() {
        dao.clearNeoWs()
    }

    fun displayNeoWs(): LiveData<List<NearEarthObject>> = dao.getNEOs()

}