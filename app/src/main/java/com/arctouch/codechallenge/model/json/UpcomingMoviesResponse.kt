package com.arctouch.codechallenge.model.json

import com.squareup.moshi.Json

data class UpcomingMoviesResponse(
        val page: Int,
        val results: List<MovieJson>,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Int
)