package app.movies.network

import app.movies.network.model.PageDto
import app.movies.network.service.TmdbService

interface MovieSdk {
    suspend fun discovery(page: Int): PageDto
}

internal class MoviesSdkImpl(
    private val service: TmdbService,
    private val apiKey: String,
) : MovieSdk {
    override suspend fun discovery(page: Int): PageDto =
        service.discover(page = page, apiKey = apiKey)
}