package app.movies.storage.dao

import androidx.paging.PagingSource
import androidx.room.*
import app.movies.storage.model.FavoriteMovieEntity
import app.movies.storage.resultmodel.FavoriteMovieWithMovie

@Dao
abstract class FavoriteMovieDao {
    @Transaction
    @Query("SELECT * FROM favorite_movies")
    abstract fun entriesPagingSource(): PagingSource<Int, FavoriteMovieWithMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun save(favoriteMovie: FavoriteMovieEntity)
}
