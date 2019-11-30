package com.arctouch.codechallenge.model.json

import com.squareup.moshi.Json

data class MovieJson(
        val id: Int,
        val title: String,
        val overview: String?,
        val genres: List<GenreJson>?,
        @Json(name = "genre_ids") val genreIds: List<Int>?,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "backdrop_path") val backdropPath: String?,
        @Json(name = "release_date") val releaseDate: String?
)