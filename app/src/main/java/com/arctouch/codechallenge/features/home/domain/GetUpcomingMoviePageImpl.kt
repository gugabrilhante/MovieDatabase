package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetUpcomingMoviePageImpl(
        private val movieRepository: Repositories.MovieRepository,
        private val setGenresToMovies: UseCases.SetGenresToMovies
) : UseCases.GetUpcomingMoviePage {

    override fun invoke(pageIndex: Int): Single<Page> {
        val genresSingle = movieRepository.getGenres()
        val pageSingle = movieRepository.getUpcomingMovies(pageIndex)
        return setGenresToMovies(pageSingle, genresSingle)
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