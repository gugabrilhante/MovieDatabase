package com.arctouch.codechallenge.features.home.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.extensions.*
import com.arctouch.codechallenge.features.detail.ui.MovieDetailsActivity
import com.arctouch.codechallenge.features.home.presentation.HomeViewModel
import com.arctouch.codechallenge.features.home.ui.adapter.HomeAdapter
import com.arctouch.codechallenge.features.home.ui.adapter.MovieListener
import com.arctouch.codechallenge.model.entity.Movie
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity(), MovieListener {

    private lateinit var viewModel: HomeViewModel

    private var adapter = HomeAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setupViews()
        registerObservables()
        savedInstanceState?.let { savedInstance: Bundle ->
            recyclerView.layoutManager?.onRestoreInstanceState(savedInstance.getParcelable("LayoutManagerInstance"))
        } ?: run {
            viewModel.onViewCreated()
        }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        recyclerView.layoutManager?.let {
            savedInstanceState.putParcelable(
                    "LayoutManagerInstance",
                    it.onSaveInstanceState()
            )
        }
    }

    private fun registerObservables() {
        viewModel.movieListLiveData.observe(this, Observer {
            adapter.addToList(it)
        })
        viewModel.isLoadingLiveData.observe(this, Observer { isLoading: Boolean ->
            if (isLoading) {
                progressBar.visible()
            } else {
                progressBar.gone()
                if (swipeRefreshLayout.isRefreshing) swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun setupViews() {
        recyclerView.adapter = adapter
        recyclerView.verticalLinearLayout(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView.reachedBottomLinearLayout(0, adapter.movieListSize - 1)) {
                    if (adapter.movieListSize > 0) recyclerView.smoothScrollBy(0, -10)
                    if (!progressBar.isVisible()) viewModel.loadNextPage()
                }
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchMovieList(query)
                return false
            }

        })
        searchView.setOnSearchClickListener {
            toolbar.setBackgroundAnimated(250, R.color.colorPrimary, R.color.white)
        }
        searchView.setOnCloseListener {
            toolbar.setBackgroundAnimated(250, R.color.white, R.color.colorPrimary)
            false
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.reloadAll()
            swipeRefreshLayout.isRefreshing = true
        }

    }

    override fun onMovieClick(movie: Movie, viewList: List<View>) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pairs = viewList.map { androidx.core.util.Pair(it, it.transitionName) }.toTypedArray()
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *pairs)
            this.startActivity(intent, activityOptionsCompat.toBundle())
        } else {
            this.startActivity(intent)
        }
    }
}
