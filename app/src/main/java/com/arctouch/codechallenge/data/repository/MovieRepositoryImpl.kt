package com.arctouch.codechallenge.data.repository

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import com.arctouch.codechallenge.model.mappers.PageMapper
import io.reactivex.Observable
import io.reactivex.Single

class MovieRepositoryImpl(
        private val cacheDataSource: Repositories.CacheDataSource,
        private val serverDataSource: Repositories.ServerDataSource,
        private val mapper: PageMapper = PageMapper()
) : Repositories.MovieRepository {
    override fun saveGenres(genres: List<Genre>) {
        cacheDataSource.saveGenres(genres)
    }

    override fun getMovies(page: Int): Single<Page> {
        return serverDataSource.getUpcomingMovies().map { mapper.fromJsonResponse(it) }
    }

    override fun getGenres(): Single<List<Genre>> =
            cacheDataSource.getGenres()?.let {
                return@getGenres Single.just(it)
            } ?: kotlin.run {
                return@getGenres serverDataSource.getGenres().map { mapper.fromGenreJsonList(it.genres) }
            }
}