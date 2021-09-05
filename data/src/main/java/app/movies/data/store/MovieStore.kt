package app.movies.data.store

import app.movies.data.mapper.MovieDtoToMovieEntityMapper
import app.movies.data.repository.network.MovieNetworkRepository
import app.movies.data.repository.storage.MovieStorageRepository
import app.movies.storage.model.MovieEntity

interface MovieStore {
    suspend fun fetch(page: Int): List<MovieEntity>
}

internal class MovieStoreImpl constructor(
    private val networkRepository: MovieNetworkRepository,
    private val storageRepository: MovieStorageRepository,
    private val movieDtoToMovieEntityMapper: MovieDtoToMovieEntityMapper,
) : MovieStore {
    override suspend fun fetch(page: Int): List<MovieEntity> {
        val pageDto = networkRepository.discover(page.inc())
        val movieEntities = pageDto.results
            .map { dto -> movieDtoToMovieEntityMapper.map(dto) }
            .map { entity -> entity.copy(page = pageDto.page.dec()) }

        storageRepository.updateEntriesByPage(
            page = pageDto.page,
            entries = movieEntities
        )

        return movieEntities
    }
}
