package com.arctouch.codechallenge.data.api

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.model.json.GenreResponse
import com.arctouch.codechallenge.model.json.MovieJson
import com.arctouch.codechallenge.model.json.MoviesResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class ServerDataSourceImpl : Repositories.ServerDataSource {

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

    override fun getUpcomingMovies(page: Long): Single<MoviesResponse> {
        return api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page, TmdbApi.DEFAULT_REGION)
    }

    override fun serachMovie(name: String, page: Long): Single<MoviesResponse> {
        return api.searchMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, name, page)
    }

    override fun getMovie(): Single<MovieJson> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}