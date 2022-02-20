package com.ugene.rickandmorty.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LocationEntity(
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "name") val name: String = "",
    @field:Json(name = "type") val type: String = "",
    @field:Json(name = "dimension") val dimension: String = ""
)

@JsonClass(generateAdapter = true)
class BaseInfo(
    @field:Json(name = "count") val count: Int = 0,
    @field:Json(name = "pages") val pages: Int = 0,
    @field:Json(name = "next") val next: String? = "",
    @field:Json(name = "prev") val prev: String? = "",
)

@JsonClass(generateAdapter = true)
class BaseEntity<T>(
    @field:Json(name = "info") val info: BaseInfo,
    @field:Json(name = "results") val results: List<T>,
)

typealias LocationsResult = BaseEntity<LocationEntity>

