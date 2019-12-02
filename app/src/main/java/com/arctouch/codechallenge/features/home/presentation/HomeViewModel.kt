package com.arctouch.codechallenge.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import com.arctouch.codechallenge.model.entity.Page
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
        private val getUpcomingMoviePage: UseCases.GetUpcomingMoviePage,
        private val searchMovie: UseCases.SearchMovie,
        private val isGenresCached: UseCases.IsGenresCached,
        private val updateGenres: UseCases.UpdateGenres
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _isDataReloaded = MutableLiveData<Boolean>()
    val isDataReloaded: LiveData<Boolean> = _isDataReloaded

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    private val _movieListLiveData = MutableLiveData<List<Movie>>()
    val movieListLiveData: LiveData<List<Movie>> = _movieListLiveData

    private var searchType: MovieSearchType = MovieSearchType.UPCOMING
    private var lastQuery: String = ""

    private var currentPage: Int = 1
    private var requestedPage: Int = 1

    fun onViewCreated() {
        loadMovies()
    }

    fun loadNextPage() {
        if (currentPage == requestedPage) {
            requestedPage++
            when (searchType) {
                MovieSearchType.UPCOMING -> loadMovies()
                MovieSearchType.SEARCH -> loadSearchMovie(lastQuery)
            }
        }
    }

    fun searchMovieList(query: String) {
        resetPages()
        lastQuery = query
        searchType = MovieSearchType.SEARCH
        loadSearchMovie(query)
    }

    fun reloadAll() {
        resetPages()
        searchType = MovieSearchType.UPCOMING
        loadMovies()
    }

    private fun resetPages() {
        currentPage = 0
        requestedPage = 1
    }

    private fun saveGenres(genres: List<Genre>) {
        if (!isGenresCached()) {
            compositeDisposable.add(
                    updateGenres(genres)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe({}, {})
            )
        }
    }

    private fun loadMovies() {
        _isLoadingLiveData.value = true
        compositeDisposable.add(
                getUpcomingMoviePage(requestedPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ page ->
                            fetchMovieToView(page)
                        }, {})
        )
    }

    private fun loadSearchMovie(query: String) {
        _isLoadingLiveData.value = true
        compositeDisposable.add(
                searchMovie(query, requestedPage.toLong())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ page ->
                            fetchMovieToView(page)
                        }, {})
        )
    }

    private fun fetchMovieToView(page: Page) {
        currentPage = page.index
        requestedPage = page.index
        if (page.movieList.isNotEmpty()) _isDataReloaded.value = (currentPage == 1)
        _movieListLiveData.value = page.movieList
        page.genres?.let { saveGenres(it) }
        _isLoadingLiveData.value = false
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}