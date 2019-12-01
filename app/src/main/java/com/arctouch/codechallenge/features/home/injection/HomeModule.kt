package com.arctouch.codechallenge.features.home.injection

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases

interface HomeModule {
    val getMoviePage: UseCases.GetMoviePage
    val updateGenres: UseCases.UpdateGenres
    val isGenresCached: UseCases.IsGenresCached
    val movieRepository: Repositories.MovieRepository
    val cacheDataSource: Repositories.CacheDataSource
    val serverDataSource: Repositories.ServerDataSource
}