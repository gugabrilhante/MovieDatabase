package com.arctouch.codechallenge.model.json

import com.squareup.moshi.Json


data class GenreJson(
        @Json(name = "id")val id: Int,
        @Json(name = "name")val name: String)