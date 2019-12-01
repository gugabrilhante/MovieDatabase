package com.arctouch.codechallenge.features.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.mock.MockData
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyList

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val getUpcomingMoviePage: UseCases.GetUpcomingMoviePage = mock()
    private val searchMovie: UseCases.SearchMovie = mock()
    private val isGenresCached: UseCases.IsGenresCached = mock()
    private val updateGenres: UseCases.UpdateGenres = mock()

    private lateinit var viewModel: HomeViewModel

    private val pageSingle = Single.just(MockData.mockPageWithGenres)

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        viewModel = HomeViewModel(getUpcomingMoviePage, searchMovie, isGenresCached, updateGenres)
        whenever(getUpcomingMoviePage.invoke(any())).thenReturn(pageSingle)
    }

    @Test
    fun `on view created load upcoming movies`() {
        viewModel.onViewCreated()
        verify(getUpcomingMoviePage).invoke(any())
    }

    @Test
    fun `on reload data, load upcoming movies`(){
        viewModel.reloadAll()
        verify(getUpcomingMoviePage).invoke(any())
    }

    @Test
    fun `genres are cached only in first download`(){
        whenever(isGenresCached()).thenReturn(false)
        whenever(updateGenres(any())).thenReturn(Completable.fromAction {  })
        viewModel.onViewCreated()
        verify(isGenresCached).invoke()
        verify(updateGenres).invoke(anyList())
    }
}