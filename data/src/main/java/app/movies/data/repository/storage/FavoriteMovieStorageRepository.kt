package app.movies.data.repository.storage

import androidx.paging.PagingSource
import app.movies.storage.dao.FavoriteMovieDao
import app.movies.storage.model.FavoriteMovieEntity
import app.movies.storage.resultmodel.FavoriteMovieWithMovie
import javax.inject.Inject

interface FavoriteMovieStorageRepository {
    fun entriesPagingSource(): PagingSource<Int, FavoriteMovieWithMovie>
    suspend fun addMovieToFavorites(movieId: Long)
}

internal class FavoriteMovieStorageRepositoryImpl @Inject constructor(
    private val dao: FavoriteMovieDao,
) : FavoriteMovieStorageRepository {
    override fun entriesPagingSource(): PagingSource<Int, FavoriteMovieWithMovie> =
        dao.entriesPagingSource()

    override suspend fun addMovieToFavorites(movieId: Long) =
        dao.save(FavoriteMovieEntity(movieId = movieId))
}
