package app.movies.data.repository

import app.movies.data.repository.network.MovieNetworkRepository
import app.movies.data.repository.storage.MovieStorageRepository
import javax.inject.Inject

interface MovieRepository {

}

internal class MovieRepositoryImpl constructor(
    private val networkRepository: MovieNetworkRepository,
    private val storageRepository: MovieStorageRepository,
) : MovieRepository {

}