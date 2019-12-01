package com.arctouch.codechallenge.features.home.injection

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases

interface HomeModule {
    val getUpcomingMoviePage: UseCases.GetUpcomingMoviePage
    val searchMovie: UseCases.SearchMovie
    val setGenresToMovies: UseCases.SetGenresToMovies
    val updateGenres: UseCases.UpdateGenres
    val isGenresCached: UseCases.IsGenresCached
    val movieRepository: Repositories.MovieRepository
    val cacheDataSource: Repositories.CacheDataSource
    val serverDataSource: Repositories.ServerDataSource
}