package app.movies.domain.observer

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import app.movies.data.mapper.MovieEntityToMovieModelMapper
import app.movies.data.model.Movie
import app.movies.data.repository.storage.FavoriteMovieStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObservePagedFavoriteMovies @Inject constructor(
    private val storageRepository: FavoriteMovieStorageRepository,
    private val movieEntityToMovieModelMapper: MovieEntityToMovieModelMapper,
): ObservePaged<ObservePagedFavoriteMovies.Params, PagingData<Movie>>() {
    override fun createFlow(params: Params): Flow<PagingData<Movie>> = Pager(
        config = params.pagingConfig,
        pagingSourceFactory = storageRepository::entriesPagingSource,
    )
        .flow
        .map { data ->
            data.map { entity ->
                movieEntityToMovieModelMapper.map(entity.movie)
            }
        }


    data class Params(
        val pagingConfig: PagingConfig,
    )
}
