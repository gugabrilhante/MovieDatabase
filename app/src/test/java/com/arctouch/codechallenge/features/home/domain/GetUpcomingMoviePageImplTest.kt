package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.mock.MockData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetUpcomingMoviePageImplTest {

    private val movieRepository: Repositories.MovieRepository = mock()
    private val setGenresToMovies: UseCases.SetGenresToMovies = mock()

    private lateinit var getUpcomingMoviePage: GetUpcomingMoviePageImpl

    private val genreList = Single.just(MockData.mockGenreList)
    private val page = Single.just(MockData.mockPageNoGenres)

    @Before
    fun setUp() {
        getUpcomingMoviePage = GetUpcomingMoviePageImpl(movieRepository, setGenresToMovies)
        whenever(movieRepository.getGenres()).thenReturn(genreList)
        whenever(movieRepository.getUpcomingMovies(1)).thenReturn(page)
    }

    @Test
    fun `always set genres to movies`() {
        getUpcomingMoviePage.invoke(1)
        verify(setGenresToMovies).invoke(page, genreList)
    }
}