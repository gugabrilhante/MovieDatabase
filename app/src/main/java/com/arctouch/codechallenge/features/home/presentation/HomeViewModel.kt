package com.arctouch.codechallenge.features.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.contracts.UseCases
import com.arctouch.codechallenge.model.entity.Genre
import com.arctouch.codechallenge.model.entity.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
        private val getMoviePage: UseCases.GetMoviePage,
        private val isGenresCached: UseCases.IsGenresCached,
        private val updateGenres: UseCases.UpdateGenres
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData = _isLoadingLiveData

    private val _movieListLiveData = MutableLiveData<List<Movie>>()
    val movieListLiveData = _movieListLiveData

    private var currentPage: Int = 1
    private var requestedPage: Int = 1

    fun onViewCreated() {
        loadMovies()
    }

    fun reloadAll() {
        currentPage = 1
        requestedPage = 1
        loadMovies()
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
                getMoviePage(requestedPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ page ->
                            _movieListLiveData.value = page.movieList
                            currentPage = page.index
                            requestedPage = page.index
                            page.genres?.let { saveGenres(it) }
                            _isLoadingLiveData.value = false
                        }, {})
        )
    }

    fun loadNextPage() {
        if (currentPage == requestedPage) {
            requestedPage++
            loadMovies()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun searchMovieList(query: String) {

    }

}