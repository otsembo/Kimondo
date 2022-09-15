package com.otsembo.kimondo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "astronomy_picture_of_day")
data class Apod (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val title: String
)