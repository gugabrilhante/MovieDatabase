package com.arctouch.codechallenge.features.detail.injection

import android.content.Intent
import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.features.detail.data.MovieExtraWrapper

class MovieDetailsModuleImpl(private val intent: Intent) : MovieDetailsModule {
    override val movieExtra: Repositories.MovieExtra by lazy {
        MovieExtraWrapper(intent)
    }
}