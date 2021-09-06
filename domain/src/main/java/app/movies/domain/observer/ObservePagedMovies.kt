package app.movies.domain.observer

import androidx.paging.*
import app.movies.data.mapper.MovieEntityToMovieModelMapper
import app.movies.data.model.Movie
import app.movies.data.repository.storage.MovieStorageRepository
import app.movies.domain.interactor.UpdateMovies
import app.movies.domain.mediator.PagedMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ObservePagedMovies @Inject constructor(
    private val storageRepository: MovieStorageRepository,
    private val updateMovies: UpdateMovies,
    private val movieEntityToMovieModelMapper: MovieEntityToMovieModelMapper,
): ObservePaged<ObservePagedMovies.Params, PagingData<Movie>>() {
    override fun createFlow(params: Params): Flow<PagingData<Movie>> = Pager(
        config = params.pagingConfig,
        remoteMediator = PagedMediator { page ->
            updateMovies(UpdateMovies.Params(page = page, force = true))
        },
        pagingSourceFactory = storageRepository::entriesPagingSource,
    )
        .flow
        .map { data ->
            data.map { entity ->
                movieEntityToMovieModelMapper.map(entity)
            }
        }

    data class Params(
        val pagingConfig: PagingConfig,
    )
}