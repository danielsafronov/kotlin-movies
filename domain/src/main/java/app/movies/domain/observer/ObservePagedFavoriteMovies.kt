package app.movies.domain.observer

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import app.movies.data.mapper.FavoriteMovieEntityToFavoriteMovieModelMapper
import app.movies.data.mapper.MovieEntityToMovieModelMapper
import app.movies.data.repository.storage.FavoriteMovieStorageRepository
import app.movies.data.resultmodel.MovieWithFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObservePagedFavoriteMovies @Inject constructor(
    private val storageRepository: FavoriteMovieStorageRepository,
    private val movieEntityToMovieModelMapper: MovieEntityToMovieModelMapper,
    private val favoriteMovieEntityToFavoriteMovieModelMapper: FavoriteMovieEntityToFavoriteMovieModelMapper,
): ObservePaged<ObservePagedFavoriteMovies.Params, PagingData<MovieWithFavorite>>() {
    override fun createFlow(params: Params): Flow<PagingData<MovieWithFavorite>> = Pager(
        config = params.pagingConfig,
        pagingSourceFactory = storageRepository::entriesPagingSource,
    )
        .flow
        .map { data ->
            data.map { entity ->
                MovieWithFavorite(
                    movie = movieEntityToMovieModelMapper.map(entity.movie),
                    favorite = favoriteMovieEntityToFavoriteMovieModelMapper.map(entity.favorite),
                )
            }
        }


    data class Params(
        val pagingConfig: PagingConfig,
    )
}
