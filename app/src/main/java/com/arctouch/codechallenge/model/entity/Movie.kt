package com.arctouch.codechallenge.model.entity

import java.io.Serializable

data class Movie(
        val id: Int,
        val title: String,
        val overview: String?,
        val genres: List<Genre>?,
        val genreIds: List<Int>?,
        val posterPath: String?,
        val backdropPath: String?,
        val releaseDate: String?
) : Serializable {

    val genreText: String
        get() = genres?.joinToString(separator = ", ") { it.name } ?: ""

}