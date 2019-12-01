package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.model.entity.Genre
import io.reactivex.Completable

class UpdateGenresImpl(
        private val repository: Repositories.MovieRepository
) : UseCases.UpdateGenres {

    override fun invoke(genres: List<Genre>): Completable {
        return Completable.fromAction { if (!repository.isGenresCached) repository.saveGenres(genres) }
    }

}