package com.arctouch.codechallenge.data.repository

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import com.arctouch.codechallenge.model.mappers.PageMapper
import io.reactivex.Single

class MovieRepository(
        private val cacheDataSource: Repositories.CacheDataSource,
        private val serverDataSource: Repositories.ServerDataSource,
        private val mapper: PageMapper = PageMapper()
) : Repositories.MovieRepository {
    override fun saveGenres(genres: List<Genre>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovies(page: Int): Page {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getGenres(): Single<List<Genre>> =
            cacheDataSource.getGenres()?.let {
                return@getGenres Single.just(it)
            } ?: kotlin.run {
                return@getGenres serverDataSource.getGenres().map { mapper.fromGenreJsonList(it.genres) }
            }
}