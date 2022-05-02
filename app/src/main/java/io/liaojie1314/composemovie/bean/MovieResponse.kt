package io.liaojie1314.composemovie.bean

data class MovieResponse<T>(
    var TotalResults: String = "0",
    var Response: String = "false",
    var Error: String = "null",
    var Search: T
) {
    override fun toString(): String {
        return "MovieSearchResponse(TotalResults=$TotalResults, Response=$Response, Error=$Error, Search=$Search)"
    }
}