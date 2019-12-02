package com.arctouch.codechallenge.features.home.injection

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.data.api.ServerDataSourceImpl
import com.arctouch.codechallenge.data.caches.CacheWrapper
import com.arctouch.codechallenge.data.repository.MovieRepositoryImpl
import com.arctouch.codechallenge.features.home.domain.*

class HomeModuleImpl : HomeModule {
    override val setGenresToMovies: UseCases.SetGenresToMovies by lazy {
        SetGenresToMoviesImpl()
    }
    override val getUpcomingMoviePage: UseCases.GetUpcomingMoviePage by lazy {
        GetUpcomingMoviePageImpl(movieRepository, setGenresToMovies)
    }
    override val searchMovie: UseCases.SearchMovie by lazy {
        SearchMovieImpl(movieRepository, setGenresToMovies)
    }
    override val updateGenres: UseCases.UpdateGenres by lazy {
        UpdateGenresImpl(movieRepository)
    }
    override val isGenresCached: UseCases.IsGenresCached by lazy {
        IsGenresCachedImpl(movieRepository)
    }
    override val movieRepository: Repositories.MovieRepository by lazy {
        MovieRepositoryImpl(cacheDataSource, serverDataSource)
    }
    override val cacheDataSource: Repositories.CacheDataSource by lazy {
        CacheWrapper()
    }
    override val serverDataSource: Repositories.ServerDataSource by lazy {
        ServerDataSourceImpl()
    }
}