package com.arctouch.codechallenge.contracts

import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import com.arctouch.codechallenge.model.json.GenreResponse
import com.arctouch.codechallenge.model.json.MovieJson
import com.arctouch.codechallenge.model.json.MoviesResponse
import io.reactivex.Single

interface Repositories {
    interface MovieRepository {
        val isGenresCached: Boolean
        fun getUpcomingMovies(page: Int): Single<Page>
        fun getGenres(): Single<List<Genre>>
        fun saveGenres(genres: List<Genre>)
        fun searchMovie(name: String, page: Long): Single<Page>
    }

    interface CacheDataSource {
        fun getGenres(): List<Genre>?
        fun saveGenres(genres: List<Genre>)
    }

    interface ServerDataSource {
        fun getGenres(): Single<GenreResponse>
        fun getUpcomingMovies(page:Long): Single<MoviesResponse>
        fun searchMovie(name: String, page: Long): Single<MoviesResponse>
    }

    interface MovieExtra {
        fun getMovie(): Movie
    }
}