package com.arctouch.codechallenge.home.presentation

import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.contracts.UseCases

class HomeViewModel(
        private val getMoviePage: UseCases.GetMoviePage
) : ViewModel() {

    private var page: Int = 0

    fun onViewCreated() {

    }

}