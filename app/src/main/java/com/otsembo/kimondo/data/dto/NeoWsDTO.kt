package com.otsembo.kimondo.data.dto

data class NeoWsDTO(
    val element_count: Int,
    val links: Links,
    val near_earth_objects: Map<String, List<NearEarthObjectData>>
)