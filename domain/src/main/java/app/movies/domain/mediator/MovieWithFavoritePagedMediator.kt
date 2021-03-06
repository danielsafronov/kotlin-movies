package app.movies.domain.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import app.movies.storage.resultmodel.MovieWithFavorite

@OptIn(ExperimentalPagingApi::class)
class MovieWithFavoritePagedMediator<T>(
    private val fetch: suspend (page: Int) -> Unit
) : RemoteMediator<Int, T>() where T: MovieWithFavorite {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, T>
    ): MediatorResult {
        val nextPage = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.movie.page + 1
            }
        }

        return try {
            fetch(nextPage)
            MediatorResult.Success(endOfPaginationReached = false)
        } catch (t: Throwable) {
            MediatorResult.Error(t)
        }
    }
}