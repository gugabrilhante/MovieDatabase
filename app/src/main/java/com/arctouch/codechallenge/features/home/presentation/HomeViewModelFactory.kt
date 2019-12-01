package com.arctouch.codechallenge.features.home.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arctouch.codechallenge.features.home.injection.HomeInjection
import com.arctouch.codechallenge.features.home.injection.HomeModule

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
                module.getUpcomingMoviePage,
                module.searchMovie,
                module.isGenresCached,
                module.updateGenres
        ) as T
    }

    private val module: HomeModule
        get() = (context as HomeInjection).module
}