package app.movies.data.repository.storage

import app.movies.storage.dao.MovieDao

internal interface MovieStorageRepository  {

}

internal class MovieStorageRepositoryImpl(
    private val dao: MovieDao,
): MovieStorageRepository {

}
