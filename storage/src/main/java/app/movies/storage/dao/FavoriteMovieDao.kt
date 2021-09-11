package app.movies.storage.dao

import androidx.paging.PagingSource
import androidx.room.*
import app.movies.storage.model.FavoriteMovieEntity
import app.movies.storage.resultmodel.FavoriteWithMovie

@Dao
abstract class FavoriteMovieDao {
    @Transaction
    @Query("SELECT * FROM favorite_movies")
    abstract fun entriesPagingSource(): PagingSource<Int, FavoriteWithMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun save(favoriteMovie: FavoriteMovieEntity)

    @Query("DELETE FROM favorite_movies WHERE id = :id")
    abstract suspend fun deleteById(id: Long)
}
