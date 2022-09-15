package com.otsembo.kimondo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mars_photos")
data class RoverPhoto(
    @PrimaryKey
    val id: Long,
    val camera: String,
    val earth_date: String,
    val img_src: String,
    val rover: String
)
