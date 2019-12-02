package com.arctouch.codechallenge.features.home.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.extensions.inflate
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeViewHolder(parent: ViewGroup, private val listener: MovieListener) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.movie_item, false)) {

    fun bind(movie: Movie) {
        val genreText = movie.genreText

        itemView.titleTextView.text = movie.title
        itemView.genresTextView.text = genreText
        itemView.releaseDateTextView.text = movie.releaseDate

        Glide.with(itemView.context)
                .load(movie.posterPath?.let { MovieImageUrlBuilder().buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(itemView.posterImageView)

        itemView.setOnClickListener {
            val viewList = listOf(itemView.posterImageView, itemView.titleTextView, itemView.genresTextView)
            listener.onMovieClick(movie, viewList)
        }
    }
}