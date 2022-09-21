package com.otsembo.kimondo.data.dto

data class Item(
    val `data`: List<Data> = ApiDefaults.getList(),
    val href: String = ApiDefaults.TEXT,
    val links: List<Link> = ApiDefaults.getList()
)