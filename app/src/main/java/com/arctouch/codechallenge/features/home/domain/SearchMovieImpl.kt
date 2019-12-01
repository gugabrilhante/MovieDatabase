package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.Single

class SearchMovieImpl(
        private val movieRepository: Repositories.MovieRepository,
        private val setGenresToMovies: UseCases.SetGenresToMovies
) : UseCases.SearchMovie {

    override fun invoke(name: String, page: Long): Single<Page> {
        val genresSingle = movieRepository.getGenres()
        val pageSingle = movieRepository.searchMovie(name, page)

        return setGenresToMovies(pageSingle, genresSingle)
    }
}