package com.arctouch.codechallenge.data.repository

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.mock.MockData
import com.arctouch.codechallenge.model.mappers.PageMapper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class MovieRepositoryImplTest {

    private val cacheDataSource: Repositories.CacheDataSource = mock()
    private val serverDataSource: Repositories.ServerDataSource = mock()
    private val mapper: PageMapper = mock()

    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        repository = MovieRepositoryImpl(cacheDataSource, serverDataSource, mapper)
    }

    @Test
    fun `if genres are null or empty return false`() {
        whenever(cacheDataSource.getGenres()).thenReturn(null)
        assertFalse(repository.isGenresCached)

        whenever(cacheDataSource.getGenres()).thenReturn(emptyList())
        assertFalse(repository.isGenresCached)
    }

    @Test
    fun `if has genre list return true`() {
        whenever(cacheDataSource.getGenres()).thenReturn(MockData.mockGenreList)
        assertTrue(repository.isGenresCached)
    }

    @Test
    fun saveGenres() {
        val genres = MockData.mockGenreList
        repository.saveGenres(genres)
        verify(cacheDataSource).saveGenres(genres)
    }

    @Test
    fun `download movies from server`() {
        whenever(serverDataSource.getUpcomingMovies(1)).thenReturn(Single.just(MockData.mockMoviesResponse))
        repository.getUpcomingMovies(1)
        verify(serverDataSource).getUpcomingMovies(1)
    }

    @Test
    fun searchMovie() {
        whenever(serverDataSource.serachMovie("query", 1)).thenReturn(Single.just(MockData.mockMoviesResponse))
        repository.searchMovie("query", 1)
        verify(serverDataSource).serachMovie("query", 1)
    }

    @Test
    fun `if genres are cached server are never called`() {
        whenever(cacheDataSource.getGenres()).thenReturn(MockData.mockGenreList)
        repository.getGenres()
        verify(serverDataSource, never()).getGenres()
    }

    @Test
    fun `if genres are not cached server is always called`() {
        whenever(cacheDataSource.getGenres()).thenReturn(null)
        whenever(serverDataSource.getGenres()).thenReturn(Single.just(MockData.mockGenreResponse))
        repository.getGenres()
        verify(serverDataSource).getGenres()
    }
}