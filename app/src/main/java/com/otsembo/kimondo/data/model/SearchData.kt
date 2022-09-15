package com.otsembo.kimondo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "search_data")
data class SearchData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val center: String,
    val date_created: String,
    val description: String,
    val description_508: String,
    val location: String,
    val media_type: String,
    val nasa_id: String,
    val photographer: String,
    val secondary_creator: String,
    val title: String
)

@Entity(tableName = "albums", foreignKeys = [ForeignKey(entity = SearchData::class, childColumns = ["searchData"], parentColumns = ["id"])])
data class Album(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val albumText: String,
    val searchData: Long
)

@Entity(tableName = "keywords", foreignKeys = [ForeignKey(entity = SearchData::class, childColumns = ["searchData"], parentColumns = ["id"])])
data class Keyword(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val keywordText: String,
    val searchData: Long
)