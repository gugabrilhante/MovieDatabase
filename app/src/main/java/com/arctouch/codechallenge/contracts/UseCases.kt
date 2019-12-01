package com.arctouch.codechallenge.contracts

import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface UseCases {
    interface GetMoviePage {
        operator fun invoke(pageIndex: Int): Single<Page>
    }
    interface UpdateGenres{
        operator fun invoke(genres:List<Genre>): Completable
    }
    interface IsGenresCached {
        operator fun invoke(): Boolean
    }
}