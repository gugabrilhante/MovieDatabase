package com.arctouch.codechallenge.data.api

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.json.GenreResponse
import com.arctouch.codechallenge.model.json.MovieJson
import com.arctouch.codechallenge.model.json.UpcomingMoviesResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class ServerDataSourceImpl :Repositories.ServerDataSource{

    private val api: TmdbApi = Retrofit.Builder()
            .baseUrl(TmdbApi.URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TmdbApi::class.java)

    override fun getGenres(): Single<GenreResponse> {
        return api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
    }

    override fun getUpcomingMovies(): Single<UpcomingMoviesResponse> {
       return api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1, TmdbApi.DEFAULT_REGION)
    }

    override fun getMovie(): Single<MovieJson> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}