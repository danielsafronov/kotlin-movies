package app.movies.data.repository.storage

import androidx.paging.PagingSource
import app.movies.storage.dao.MovieDao
import app.movies.storage.model.MovieEntity
import app.movies.storage.resultmodel.MovieWithFavorite

interface MovieStorageRepository  {
    fun entriesPagingSource(): PagingSource<Int, MovieWithFavorite>

    suspend fun updateEntriesByPage(page: Int, entries: List<MovieEntity>)
}

internal class MovieStorageRepositoryImpl(
    private val dao: MovieDao,
): MovieStorageRepository {
    override fun entriesPagingSource(): PagingSource<Int, MovieWithFavorite> =
        dao.entriesPagingSource()

    override suspend fun updateEntriesByPage(page: Int, entries: List<MovieEntity>) =
        dao.updateEntriesByPage(page, entries)
}
