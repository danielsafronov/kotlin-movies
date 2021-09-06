package app.movies.storage.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import app.movies.storage.resultmodel.FavoriteMovieWithMovie

@Dao
abstract class FavoriteMovieDao {
    @Transaction
    @Query("SELECT * FROM favorite_movies")
    abstract fun entriesPagingSource(): PagingSource<Int, FavoriteMovieWithMovie>
}
