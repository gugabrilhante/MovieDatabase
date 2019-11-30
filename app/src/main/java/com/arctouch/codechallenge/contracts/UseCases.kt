package com.arctouch.codechallenge.contracts

import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.Single

interface UseCases {
    interface GetGenres {
        operator fun invoke()
    }

    interface GetMoviePage {
        operator fun invoke(page: Int): Single<Page>
    }
}