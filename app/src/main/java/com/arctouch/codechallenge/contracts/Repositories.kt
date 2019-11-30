package com.arctouch.codechallenge.contracts

import com.arctouch.codechallenge.data.caches.Cache
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Page
import com.arctouch.codechallenge.model.json.GenreResponse
import com.arctouch.codechallenge.model.json.MovieJson
import com.arctouch.codechallenge.model.json.UpcomingMoviesResponse
import io.reactivex.Observable
import io.reactivex.Single

interface Repositories {
    interface MovieRepository{
        fun getMovies(page:Int):Page
        fun getGenres(): Single<List<Genre>>
        fun saveGenres(genres:List<Genre>)
    }
    interface CacheDataSource{
        fun getGenres() :List<Genre>?
        fun saveGenres(genres:List<Genre>)
    }
    interface ServerDataSource{
        fun getGenres(): Single<GenreResponse>
        fun getUpcomingMovies():Observable<UpcomingMoviesResponse>
        fun getMovie():Single<MovieJson>
    }
}