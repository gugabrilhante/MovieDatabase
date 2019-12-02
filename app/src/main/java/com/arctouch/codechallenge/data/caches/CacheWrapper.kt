package com.arctouch.codechallenge.data.caches

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.entity.Genre

class CacheWrapper : Repositories.CacheDataSource {
    override fun getGenres() = Cache.genres
    override fun saveGenres(genres: List<Genre>) {
        Cache.cacheGenres(genres)
    }
}