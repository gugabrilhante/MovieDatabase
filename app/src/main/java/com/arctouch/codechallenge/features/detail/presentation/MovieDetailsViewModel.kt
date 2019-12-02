package com.arctouch.codechallenge.features.detail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.entity.Movie

class MovieDetailsViewModel(
        private val movieExtra: Repositories.MovieExtra
) : ViewModel() {

    var movieLiveData = MutableLiveData<Movie>()

    fun getMovie() {
        movieLiveData.value = movieExtra.getMovie()
    }

}