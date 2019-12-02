package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.mock.MockData
import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SetGenresToMoviesImplTest {

    private lateinit var setGenresToMovies: SetGenresToMoviesImpl

    private var genreList = MockData.mockGenreList
    private var pageSingle = Single.just(MockData.mockPageNoGenres)
    private var genresSingle = Single.just(genreList)

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        setGenresToMovies = SetGenresToMoviesImpl()
    }

    @Test
    fun `test if genres are added to each movie of the list by their genreIds`() {
        val testSingleObserver = TestObserver<Page>()
        val result = setGenresToMovies(pageSingle, genresSingle)
        result.subscribe(testSingleObserver)

        val page = testSingleObserver.values()[0]
        val movie = page.movieList[0]
        assertTrue(page.genres!!.isNotEmpty())
        movie.genres!!.forEach { genre ->
            assertTrue(genreList.any { it.id == genre.id })
        }
    }

    @Test
    fun `test if list of genres are added to page object`() {
        val testSingleObserver = TestObserver<Page>()
        val result = setGenresToMovies(pageSingle, genresSingle)
        result.subscribe(testSingleObserver)

        val page = testSingleObserver.values()[0]
        assertNotNull(page.genres)
        assertTrue(page.genres!!.isNotEmpty())
    }
}