package com.arctouch.codechallenge.model.mappers

import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import com.arctouch.codechallenge.model.json.GenreJson
import com.arctouch.codechallenge.model.json.MovieJson
import com.arctouch.codechallenge.model.json.MoviesResponse

class PageMapper {

    fun fromJsonResponse(response: MoviesResponse): Page {
        return Page(
                response.page,
                fromMovieJsonList(response.results),
                response.totalResults)
    }

    private fun fromMovieJsonList(movieJsonList: List<MovieJson>) = movieJsonList.map {
        Movie(it.id.toInt(), it.title, it.overview, fromMovieGenreJsonList(it.genres), it.genreIds, it.posterPath, it.backdropPath, it.releaseDate)
    }

    private fun fromMovieGenreJsonList(genreJsonList: List<GenreJson>?) = genreJsonList?.map {
        Genre(it.id, it.name)
    }

    fun fromGenreJsonList(genreJsonList: List<GenreJson>) = genreJsonList.map {
        Genre(it.id, it.name)
    }

}