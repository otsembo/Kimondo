package com.otsembo.kimondo.data.dto

data class Item(
    val `data`: List<Data>,
    val href: String,
    val links: List<Link>
)