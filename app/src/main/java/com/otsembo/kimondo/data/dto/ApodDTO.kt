package com.otsembo.kimondo.data.dto

data class ApodDTO(
    val copyright: String = ApiDefaults.TEXT,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)