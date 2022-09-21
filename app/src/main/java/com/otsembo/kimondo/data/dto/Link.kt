package com.otsembo.kimondo.data.dto

data class Link(
    val href: String = ApiDefaults.TEXT,
    val rel: String = ApiDefaults.TEXT,
    val render: String = ApiDefaults.TEXT
)