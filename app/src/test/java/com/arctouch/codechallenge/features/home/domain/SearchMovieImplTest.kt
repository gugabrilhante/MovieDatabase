package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.mock.MockData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

import org.junit.Before

class SearchMovieImplTest {

    private val movieRepository: Repositories.MovieRepository = mock()
    private val setGenresToMovies: UseCases.SetGenresToMovies = mock()

    private lateinit var searchMovie: SearchMovieImpl

    private val genreList = Single.just(MockData.mockGenreList)
    private val page = Single.just(MockData.mockPageNoGenres)

    @Before
    fun setUp() {
        searchMovie = SearchMovieImpl(movieRepository, setGenresToMovies)
        whenever(movieRepository.getGenres()).thenReturn(genreList)
        whenever(movieRepository.searchMovie("query", 1)).thenReturn(page)
    }

    @Test
    fun `always set genres to movies`() {
        searchMovie.invoke("query", 1)
        verify(setGenresToMovies).invoke(page, genreList)
    }
}