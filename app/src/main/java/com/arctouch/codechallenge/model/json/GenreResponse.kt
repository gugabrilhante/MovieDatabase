package com.arctouch.codechallenge.model.json

import com.squareup.moshi.Json


data class GenreResponse(
        @Json(name = "genres") val genres: List<GenreJson>
)