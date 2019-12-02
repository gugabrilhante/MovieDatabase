package com.arctouch.codechallenge.data.repository

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Page
import com.arctouch.codechallenge.model.mappers.PageMapper
import io.reactivex.Single

class MovieRepositoryImpl(
        private val cacheDataSource: Repositories.CacheDataSource,
        private val serverDataSource: Repositories.ServerDataSource,
        private val mapper: PageMapper = PageMapper()
) : Repositories.MovieRepository {

    override val isGenresCached: Boolean
        get() = !cacheDataSource.getGenres().isNullOrEmpty()

    override fun saveGenres(genres: List<Genre>) {
        cacheDataSource.saveGenres(genres)
    }

    override fun getUpcomingMovies(page: Int): Single<Page> {
        return serverDataSource.getUpcomingMovies(page.toLong()).map { mapper.fromJsonResponse(it) }
    }

    override fun searchMovie(name:String, page:Long): Single<Page> {
        return serverDataSource.searchMovie(name, page).map { mapper.fromJsonResponse(it) }
    }

    override fun getGenres(): Single<List<Genre>> =
            cacheDataSource.getGenres()?.let {
                return@getGenres Single.just(it)
            } ?: kotlin.run {
                return@getGenres serverDataSource.getGenres().map { mapper.fromGenreJsonList(it.genres) }
            }
}