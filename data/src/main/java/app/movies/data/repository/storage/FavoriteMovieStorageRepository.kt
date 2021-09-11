package app.movies.data.repository.storage

import androidx.paging.PagingSource
import app.movies.storage.dao.FavoriteMovieDao
import app.movies.storage.model.FavoriteMovieEntity
import app.movies.storage.resultmodel.FavoriteWithMovie
import javax.inject.Inject

interface FavoriteMovieStorageRepository {
    fun entriesPagingSource(): PagingSource<Int, FavoriteWithMovie>
    suspend fun addMovieToFavorites(movieId: Long)
    suspend fun removeMovieFromFavorites(favoriteMovieId: Long)
}

internal class FavoriteMovieStorageRepositoryImpl @Inject constructor(
    private val dao: FavoriteMovieDao,
) : FavoriteMovieStorageRepository {
    override fun entriesPagingSource(): PagingSource<Int, FavoriteWithMovie> =
        dao.entriesPagingSource()

    override suspend fun addMovieToFavorites(movieId: Long) =
        dao.save(FavoriteMovieEntity(movieId = movieId))

    override suspend fun removeMovieFromFavorites(favoriteMovieId: Long) =
        dao.deleteById(favoriteMovieId)
}
