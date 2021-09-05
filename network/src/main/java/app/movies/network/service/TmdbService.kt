package app.movies.network.service

import app.movies.network.model.PageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("3/discover/movie")
    suspend fun discover(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): PageDto
}
