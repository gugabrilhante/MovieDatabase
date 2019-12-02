package com.arctouch.codechallenge.features.detail.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.mock.MockData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

class MovieDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val movieExtra: Repositories.MovieExtra = mock()

    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        viewModel = MovieDetailsViewModel(movieExtra)
    }

    @Test
    fun `get movie from extra`(){
        whenever(movieExtra.getMovie()).thenReturn(MockData.mockMovie)
        viewModel.getMovie()
        assertNotNull(viewModel.movieLiveData.value)
    }


}