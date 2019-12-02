package com.arctouch.codechallenge.features.home.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.model.entity.Movie

class HomeAdapter(val listener: MovieListener) : RecyclerView.Adapter<HomeViewHolder>() {

    private var movieList: MutableList<Movie> = arrayListOf()

    val movieListSize: Int
        get() = movieList.size


    fun clearList() {
        movieList = mutableListOf()
        notifyDataSetChanged()
    }

    fun addToList(list: List<Movie>) {
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder = HomeViewHolder(parent, listener)

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(movieList[position])
    }
}
