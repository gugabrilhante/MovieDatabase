package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.mock.MockData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class IsGenresCachedImplTest {

    private val movieRepository: Repositories.MovieRepository = mock()

    private lateinit var isGenresCached: IsGenresCachedImpl

    @Before
    fun setup(){
        isGenresCached = IsGenresCachedImpl(movieRepository)
    }

    @Test
    fun `return true if genres are cached`() {
        whenever(movieRepository.isGenresCached).thenReturn(true)
        assertTrue(isGenresCached())
    }

    @Test
    fun `return false if genres are not cached`(){
        whenever(movieRepository.isGenresCached).thenReturn(false)
        assertFalse(isGenresCached())
    }


}