package app.movies.storage

import android.content.Context
import android.os.Debug
import androidx.room.Room
import app.movies.storage.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    internal fun provideDatabase(
        @ApplicationContext context: Context,
    ): MoviesDatabase {
        val builder = Room.databaseBuilder(context, MoviesDatabase::class.java, "movies.db")
            .fallbackToDestructiveMigration()

        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }

        return builder.build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object DatabaseDaoModule {
    @Provides
    @Singleton
    internal fun provideMovieDao(database: MoviesDatabase): MovieDao =
        database.movieDao()
}
