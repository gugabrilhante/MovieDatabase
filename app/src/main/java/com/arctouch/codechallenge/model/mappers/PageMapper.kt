package com.arctouch.codechallenge.model.mappers

import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import com.arctouch.codechallenge.model.json.GenreJson
import com.arctouch.codechallenge.model.json.MovieJson
import com.arctouch.codechallenge.model.json.UpcomingMoviesResponse

class PageMapper {

    fun fromJsonResponse(response: UpcomingMoviesResponse) = Page(
            response.page,
            fromMovieJsonList(response.results),
            response.totalResults)

    private fun fromMovieJsonList(movieJsonList: List<MovieJson>) = movieJsonList.map {
        Movie(it.id, it.title, it.overview, fromGenreJsonList(it.genres), it.genreIds, it.posterPath, it.backdropPath, it.releaseDate)
    }

    private fun fromGenreJsonList(genreJsonList: List<GenreJson>?) = genreJsonList?.map {
        Genre(it.id, it.name)
    }
}