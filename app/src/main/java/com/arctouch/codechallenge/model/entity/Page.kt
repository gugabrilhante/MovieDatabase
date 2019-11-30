package com.arctouch.codechallenge.model.entity

import com.arctouch.codechallenge.model.json.MovieJson
import com.squareup.moshi.Json

data class Page(
        val index: Int,
        val movieList: List<Movie>,
        val totalPages: Int
)