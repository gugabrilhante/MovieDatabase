package com.arctouch.codechallenge.data.repository

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page

class MovieRepository(
        private val cacheDataSource: Repositories.CacheDataSource,
        private val serverDataSource: Repositories.ServerDataSource
) : Repositories.MovieRepository {
    override fun getMovies(page: Int): Page {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getGenres(): List<Genre> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}