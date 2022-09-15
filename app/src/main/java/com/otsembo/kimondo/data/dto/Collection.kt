package com.otsembo.kimondo.data.dto

data class Collection(
    val href: String,
    val items: List<Item>,
    val links: List<Any>,
    val metadata: Metadata,
    val version: String
)