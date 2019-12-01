package com.arctouch.codechallenge.mock

import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import com.arctouch.codechallenge.model.json.GenreJson
import com.arctouch.codechallenge.model.json.GenreResponse
import com.arctouch.codechallenge.model.json.MovieJson
import com.arctouch.codechallenge.model.json.MoviesResponse

object MockData {
    val mockGenre
        get() = Genre(0, "")

    val mockGenreList
        get() = listOf(
                Genre(0, "a"),
                Genre(1, "b"),
                Genre(2, "c")
        )

    val mockMovieList
        get() = listOf(
                Movie(1, "", "", emptyList(), listOf(1, 2), "", "", "")
        )

    val mockPage
        get() = Page(
                1,
                mockMovieList,
                2,
                null
        )

    private val mockGenreJsonList
        get() = listOf(GenreJson(0, ""))

    val mockGenreResponse: GenreResponse
        get() = GenreResponse(mockGenreJsonList)

    val mockMovieJsonList
        get() = listOf(MovieJson(1, "", "", mockGenreJsonList, listOf(0), "", "", ""))

    val mockMoviesResponse: MoviesResponse
        get() = MoviesResponse(
                1,
                mockMovieJsonList,
                1,
                1
        )
}