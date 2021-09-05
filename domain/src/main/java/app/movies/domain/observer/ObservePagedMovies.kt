package app.movies.domain.observer

import androidx.paging.*
import androidx.paging.Pager
import app.movies.data.mapper.MovieEntityToMovieModelMapper
import app.movies.data.model.Movie
import app.movies.data.repository.storage.MovieStorageRepository
import app.movies.domain.interactor.UpdateMovies
import app.movies.domain.mediator.PagedMediator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ObservePagedMovies @Inject constructor(
    private val storageRepository: MovieStorageRepository,
    private val updateMovies: UpdateMovies,
    private val movieEntityToMovieModelMapper: MovieEntityToMovieModelMapper,
) {
    private val paramState = MutableSharedFlow<Params>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val flow: Flow<PagingData<Movie>> = paramState
        .distinctUntilChanged()
        .flatMapLatest { createFlow(it) }
        .distinctUntilChanged()

    operator fun invoke(params: Params) {
        paramState.tryEmit(params)
    }

    private fun createFlow(params: Params): Flow<PagingData<Movie>> = Pager(
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