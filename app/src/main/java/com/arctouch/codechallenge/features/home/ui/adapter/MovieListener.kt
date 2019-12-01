package com.arctouch.codechallenge.features.home.ui.adapter

import android.view.View
import com.arctouch.codechallenge.model.entity.Movie

interface MovieListener {
    fun onMovieClick(movie: Movie, viewList: List<View>)
}