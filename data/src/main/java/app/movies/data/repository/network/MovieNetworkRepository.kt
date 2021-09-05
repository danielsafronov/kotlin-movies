package app.movies.data.repository.network

import app.movies.network.MovieSdk
import app.movies.network.model.PageDto

interface MovieNetworkRepository {
    suspend fun discover(page: Int): PageDto
}

internal class MovieNetworkRepositoryImpl(
    private val sdk: MovieSdk,
): MovieNetworkRepository {
    override suspend fun discover(page: Int): PageDto =
        sdk.discovery(page = page)
}
