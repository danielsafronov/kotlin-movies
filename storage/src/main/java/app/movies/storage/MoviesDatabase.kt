package app.movies.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import app.movies.storage.dao.FavoriteMovieDao
import app.movies.storage.dao.MovieDao
import app.movies.storage.model.FavoriteMovieEntity
import app.movies.storage.model.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        FavoriteMovieEntity::class,
    ],
    version = 1,
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
