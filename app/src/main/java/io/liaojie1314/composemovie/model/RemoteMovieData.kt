package io.liaojie1314.composemovie.model

import android.content.Context
import io.liaojie1314.composemovie.bean.*
import io.liaojie1314.composemovie.constant.Constants
import io.liaojie1314.composemovie.util.Utils
import kotlin.jvm.Volatile
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class RemoteMovieData private constructor(context: Context) {
    private val movieInterface: MovieService
    private val context: Context

    suspend fun searchMoviesByCoroutines(keyWorld: String): MovieResponse<List<Movie>> {
        Utils.logDebug(Utils.TAG_NETWORK, "searchMoviesByCoroutines:$keyWorld")
        return movieInterface?.requestSearchByCoroutines(keyWorld, Constants.OMDB_API_KEY)
    }

    suspend fun getMovieByCoroutines(movieID: String): MoviePro {
        Utils.logDebug(Utils.TAG_NETWORK, "getMovieByCoroutines:$movieID")
        return movieInterface?.requestDetailByCoroutines(movieID, Constants.OMDB_API_KEY)
    }

    companion object {
        @Volatile
        private var sInstance: RemoteMovieData? = null

        fun getInstance(context: Context): RemoteMovieData? {
            if (sInstance == null) {
                synchronized(RemoteMovieData::class.java) {
                    if (sInstance == null) {
                        sInstance = RemoteMovieData(context)
                    }
                }
            }
            return sInstance
        }
    }

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.OMDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieInterface = retrofit.create(MovieService::class.java)
        this.context = context
    }
}