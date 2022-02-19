package com.ugene.rickandmorty.components

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApisEntity(
    @field:Json(name = "characters") val characters: String?,
    @field:Json(name = "locations") val locations: String?,
    @field:Json(name = "episodes") val episodes: String?
)