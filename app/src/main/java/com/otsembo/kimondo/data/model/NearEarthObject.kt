package com.otsembo.kimondo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "near_earth_objects")
data class NearEarthObject(
    @PrimaryKey
    val id: Long,
    val absolute_magnitude_h: Double,
    val estimated_diameter_max: Double,
    val estimated_diameter_min: Double,
    val is_potentially_hazardous_asteroid: Boolean,
    val name: String,
    val nasa_jpl_url: String,
)
