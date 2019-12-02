package com.arctouch.codechallenge.features.detail.injection

import com.arctouch.codechallenge.contracts.Repositories

interface MovieDetailsModule {
    val movieExtra: Repositories.MovieExtra
}