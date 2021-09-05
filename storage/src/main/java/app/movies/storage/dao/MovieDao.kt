package app.movies.storage.dao

import androidx.paging.PagingSource
import androidx.room.*
import app.movies.storage.model.MovieEntity

@Dao
abstract class MovieDao {
    @Query("SELECT * FROM movies ORDER BY page ASC, id ASC")
    abstract fun entriesPagingSource(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies WHERE page = :page")
    abstract suspend fun deleteEntriesByPage(page: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: List<MovieEntity>)

    @Transaction
    open suspend fun updateEntriesByPage(page: Int, entries: List<MovieEntity>) {
        deleteEntriesByPage(page)
        insertAll(entries)
    }
}
