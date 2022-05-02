package io.liiaojie1314.composemovie.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import io.liaojie1314.composemovie.R
import io.liaojie1314.composemovie.bean.Movie
import io.liaojie1314.composemovie.bean.MoviePro
import io.liaojie1314.composemovie.model.RemoteMovieData
import io.liaojie1314.composemovie.util.Utils

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val remoteMovieData = RemoteMovieData.getInstance(application.applicationContext)

    private var movieLiveData = MutableLiveData<List<Movie>>()
    private var movieProLiveData = MutableLiveData<MoviePro>()

    val movies: LiveData<List<Movie>> = movieLiveData
    val moviePro: LiveData<MoviePro> = movieProLiveData

    suspend fun searchMoviesComposeCoroutines(keyWorld: String) {
        Utils.logDebug(Utils.TAG_SEARCH, "MovieModel searchMoviesComposeCoroutines with keyWord:$keyWorld")
        if (!Utils.ensureNetworkAvailable(getApplication())) return

        val gotMovies = remoteMovieData?.searchMoviesByCoroutines(keyWorld)?.Search
        Utils.logDebug(
            Utils.TAG_SEARCH,
            "MovieModel searchMoviesCoroutines gotMovies:$gotMovies"
        )

        if (gotMovies != null)
            movieLiveData.value = gotMovies
        else
            Toast.makeText(getApplication(), R.string.search_none, Toast.LENGTH_SHORT)
                .show()
    }

    suspend fun getMovieComposeCoroutines(id: String) {
        Utils.logDebug(Utils.TAG_SEARCH, "MovieModel getMovieComposeCoroutines with id:$id")
        if (!Utils.ensureNetworkAvailable(getApplication())) return

        val gotMovie = remoteMovieData?.getMovieByCoroutines(id)
        Utils.logDebug(Utils.TAG_SEARCH, "MovieModel getMovieComposeCoroutines movie:$gotMovie")

        if (gotMovie != null)
            movieProLiveData.value = gotMovie
        else
            Toast.makeText(getApplication(), R.string.search_none, Toast.LENGTH_SHORT)
                .show()
    }
}