package com.arctouch.codechallenge.features.detail.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.features.detail.presentation.MovieDetailsViewModel
import com.arctouch.codechallenge.extensions.animateAlpha
import com.arctouch.codechallenge.features.detail.injection.MovieDetailsInjection
import com.arctouch.codechallenge.features.detail.injection.MovieDetailsModule
import com.arctouch.codechallenge.features.detail.injection.MovieDetailsModuleImpl
import com.arctouch.codechallenge.features.detail.presentation.MovieDetailsViewModelFactory
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsInjection {

    override val module: MovieDetailsModule by lazy {
        MovieDetailsModuleImpl(intent)
    }

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        viewModel = getViewModel()
        registerObservables()
        viewModel.getMovie()
    }

    private fun registerObservables() {
        registerMovieObservable()
    }

    private fun registerMovieObservable() {
        viewModel.movieLiveData.observe(this, Observer {
            titleTextView.text = it.title
            genreTextView.text = it.genreText
            releaseDateTextView.text = it.releaseDate
            overviewTextView.text = it.overview
            it.posterPath?.let { poster: String ->
                Glide.with(this)
                        .load(MovieImageUrlBuilder().buildPosterUrl(poster))
                        .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                        .into(movieImageView)
            }
            it.backdropPath?.let { poster: String ->
                Glide.with(this)
                        .load(MovieImageUrlBuilder().buildPosterUrl(poster))
                        .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                        .into(backImageView)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        showOverViewA()
    }

    private fun showOverViewA() {
        overviewTextView.animateAlpha(1f, 250)
    }

    private fun getViewModel() =
            ViewModelProviders.of(this, MovieDetailsViewModelFactory(this)).get(MovieDetailsViewModel::class.java)
}
