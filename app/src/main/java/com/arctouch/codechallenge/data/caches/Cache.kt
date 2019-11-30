package com.arctouch.codechallenge.data.caches

import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.json.GenreJson

object Cache {

    var genres:List<Genre>? = listOf<Genre>()

    fun cacheGenres(genres: List<Genre>) {
        Cache.genres = genres
    }
}
