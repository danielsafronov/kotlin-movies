package app.movies.data

import app.movies.data.mapper.MovieDtoToMovieEntityMapper
import app.movies.data.repository.network.MovieNetworkRepository
import app.movies.data.repository.network.MovieNetworkRepositoryImpl
import app.movies.data.repository.storage.MovieStorageRepository
import app.movies.data.repository.storage.MovieStorageRepositoryImpl
import app.movies.data.store.MovieStore
import app.movies.data.store.MovieStoreImpl
import app.movies.network.MovieSdk
import app.movies.storage.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    internal fun provideMovieNetworkRepository(
        sdk: MovieSdk,
    ): MovieNetworkRepository =
        MovieNetworkRepositoryImpl(
            sdk = sdk,
        )

    @Provides
    @Singleton
    internal fun provideMovieStorageRepository(
        dao: MovieDao,
    ): MovieStorageRepository =
        MovieStorageRepositoryImpl(
            dao = dao,
        )

    @Provides
    @Singleton
    internal fun provideMovieStore(
        networkRepository: MovieNetworkRepository,
        storageRepository: MovieStorageRepository,
        movieDtoToMovieEntityMapper: MovieDtoToMovieEntityMapper,
    ): MovieStore = MovieStoreImpl(
        networkRepository = networkRepository,
        storageRepository = storageRepository,
        movieDtoToMovieEntityMapper = movieDtoToMovieEntityMapper,
    )
}
