package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.Single

class SearchMovieImpl(private val movieRepository: Repositories.MovieRepository) : UseCases.SearchMovie {

    override fun invoke(name: String, page: Long): Single<Page> = movieRepository.searchMovie(name, page)
}