package app.movies.data.repository.network

import app.movies.network.MovieSdk

internal interface MovieNetworkRepository {
}

internal class MovieNetworkRepositoryImpl(
    private val sdk: MovieSdk,
): MovieNetworkRepository {

}
