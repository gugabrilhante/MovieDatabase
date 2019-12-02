package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class SetGenresToMoviesImpl:UseCases.SetGenresToMovies {

    override fun invoke(singlePage: Single<Page>, singleGenres: Single<List<Genre>>): Single<Page> {
        return singlePage.zipWith(singleGenres, BiFunction<Page, List<Genre>, Page> { page, genreList ->
            Page(
                    page.index,
                    page.movieList.map { getMovieGenreList(it, genreList) },
                    page.totalPages,
                    genreList
            )
        })
    }

    private fun getMovieGenreList(movie: Movie, genreList: List<Genre>) = Movie(
            movie.id,
            movie.title,
            movie.overview,
            genreList.filter { movie.genreIds?.contains(it.id) == true },
            movie.genreIds,
            movie.posterPath,
            movie.backdropPath,
            movie.releaseDate)
}