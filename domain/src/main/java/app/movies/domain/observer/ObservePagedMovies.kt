package app.movies.domain.observer

import androidx.paging.*
import app.movies.data.mapper.FavoriteMovieEntityToFavoriteMovieModelMapper
import app.movies.data.mapper.MovieEntityToMovieModelMapper
import app.movies.data.repository.storage.MovieStorageRepository
import app.movies.data.resultmodel.MovieWithFavorite
import app.movies.domain.interactor.UpdateMovies
import app.movies.domain.mediator.MovieWithFavoritePagedMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ObservePagedMovies @Inject constructor(
    private val storageRepository: MovieStorageRepository,
    private val updateMovies: UpdateMovies,
    private val movieEntityToMovieModelMapper: MovieEntityToMovieModelMapper,
    private val favoriteMovieEntityToFavoriteMovieModelMapper: FavoriteMovieEntityToFavoriteMovieModelMapper,
) : ObservePaged<ObservePagedMovies.Params, PagingData<MovieWithFavorite>>() {
    override fun createFlow(params: Params): Flow<PagingData<MovieWithFavorite>> = Pager(
        config = params.pagingConfig,
        remoteMediator = MovieWithFavoritePagedMediator { page ->
            updateMovies(UpdateMovies.Params(page = page, force = true))
        },
        pagingSourceFactory = storageRepository::entriesPagingSource,
    )
        .flow
        .map { data ->
            data.map { entity ->
                MovieWithFavorite(
                    movie = movieEntityToMovieModelMapper.map(entity.movie),
                    favorite = entity.favorite?.let {
                        favoriteMovieEntityToFavoriteMovieModelMapper.map(it)
                    },
                )
            }
        }

    data class Params(
        val pagingConfig: PagingConfig,
    )
}