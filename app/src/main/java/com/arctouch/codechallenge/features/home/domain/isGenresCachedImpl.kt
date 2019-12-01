package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases

class isGenresCachedImpl(
        private val movieRepository: Repositories.MovieRepository
) : UseCases.IsGenresCached {
    override fun invoke() = movieRepository.isGenresCached
}