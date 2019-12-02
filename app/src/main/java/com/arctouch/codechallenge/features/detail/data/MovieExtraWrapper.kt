package com.arctouch.codechallenge.features.detail.data

import android.content.Intent
import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.entity.Movie

class MovieExtraWrapper(private val intent: Intent) : Repositories.MovieExtra {
    override fun getMovie(): Movie {
        return intent.getSerializableExtra("movie") as Movie
    }
}