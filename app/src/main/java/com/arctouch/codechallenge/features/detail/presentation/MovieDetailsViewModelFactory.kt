package com.arctouch.codechallenge.features.detail.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arctouch.codechallenge.features.detail.injection.MovieDetailsInjection
import com.arctouch.codechallenge.features.detail.injection.MovieDetailsModule
import com.arctouch.codechallenge.features.home.injection.HomeInjection
import com.arctouch.codechallenge.features.home.injection.HomeModule

@Suppress("UNCHECKED_CAST")
class MovieDetailsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(module.movieExtra) as T
    }

    private val module: MovieDetailsModule
        get() = (context as MovieDetailsInjection).module
}