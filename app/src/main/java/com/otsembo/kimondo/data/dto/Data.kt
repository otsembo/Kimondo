package com.otsembo.kimondo.data.dto

data class Data(
    val album: List<String> = ApiDefaults.getList(),
    val center: String = ApiDefaults.TEXT,
    val date_created: String = ApiDefaults.TEXT,
    val description: String = ApiDefaults.TEXT,
    val description_508: String = ApiDefaults.TEXT,
    val keywords: List<String> = ApiDefaults.getList(),
    val location: String = ApiDefaults.TEXT,
    val media_type: String = ApiDefaults.TEXT,
    val nasa_id: String = ApiDefaults.TEXT,
    val photographer: String = ApiDefaults.TEXT,
    val secondary_creator: String = ApiDefaults.TEXT,
    val title: String = ApiDefaults.TEXT
)

object ApiDefaults{
    const val TEXT = ""
    const val NUMBER = 0

     fun <T> getList(): List<T> = emptyList()

}