package com.arctouch.codechallenge.features.home.domain

import com.arctouch.codechallenge.contracts.Repositories
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetMoviePageImpl(
        private val movieRepository: Repositories.MovieRepository
) : UseCases.GetMoviePage {
    override fun invoke(pageIndex: Int): Single<Page> {
        val genresSingle = movieRepository.getGenres()
        val pageSingle = movieRepository.getMovies(pageIndex)
        return pageSingle.zipWith(genresSingle, BiFunction<Page, List<Genre>, Page> { page, genreList ->
            mapPageToGenreList(page, genreList)
        })
    }

    private fun mapPageToGenreList(page: Page, genreList: List<Genre>)
            = page.copy(movieList = page.movieList.map { getMovieGenreList(it, genreList) }, genres = genreList)


    private fun getMovieGenreList(movie: Movie, genreList: List<Genre>)
            = movie.copy(genres = genreList.filter { movie.genreIds?.contains(it.id) == true })

}