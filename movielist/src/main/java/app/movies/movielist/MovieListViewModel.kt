package app.movies.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.movies.data.resultmodel.MovieWithFavorite
import app.movies.domain.interactor.AddMovieToFavorite
import app.movies.domain.interactor.RemoveMovieFromFavorite
import app.movies.domain.observer.ObservePagedFavoriteMovies
import app.movies.domain.observer.ObservePagedMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieListViewModel @Inject constructor(
    observePagedMovies: ObservePagedMovies,
    observePagedFavoriteMovies: ObservePagedFavoriteMovies,
    private val addMovieToFavorite: AddMovieToFavorite,
    private val removeMovieFromFavorite: RemoveMovieFromFavorite,
) : ViewModel() {
    private val mode: MutableStateFlow<MovieListState.Mode> = MutableStateFlow(MovieListState.Mode.All)
    private val loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val pagedMovies = observePagedMovies.flow.cachedIn(viewModelScope)
    private val pagedFavoriteMovies = observePagedFavoriteMovies.flow.cachedIn(viewModelScope)
    private val pendingActions = MutableSharedFlow<MovieListAction>()

    val state: StateFlow<MovieListState> = combine(
        mode,
        loading,
    ) { mode, loading ->
        MovieListState(
            mode = mode,
            loading = loading,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MovieListState.Empty,
    )

    var movies: Flow<PagingData<MovieWithFavorite>> = pagedMovies;

    init {
        viewModelScope.launch {
            state.collect { state ->
                when (state.mode) {
                    MovieListState.Mode.All -> {
                        movies = pagedMovies
                        observePagedMovies(ObservePagedMovies.Params(PAGING_CONFIG))
                    }
                    MovieListState.Mode.Favorites -> {
                        movies = pagedFavoriteMovies
                        observePagedFavoriteMovies(ObservePagedFavoriteMovies.Params(PAGING_CONFIG))
                    }
                }
            }
        }

        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    is MovieListAction.AddToFavoriteAction -> addMovieToFavorite(action.movieId)
                    is MovieListAction.RemoveFromFavorite -> removeMovieFromFavorite(action.favoriteMovieId)
                }
            }
        }
    }

    fun setMode(mode: MovieListState.Mode) {
        this.mode.value = mode
    }

    fun submitAction(action: MovieListAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    private suspend fun addMovieToFavorite(movieId: Long) {
        addMovieToFavorite(AddMovieToFavorite.Params(movieId))
    }

    private suspend fun removeMovieFromFavorite(movieId: Long) {
        removeMovieFromFavorite(RemoveMovieFromFavorite.Params(movieId))
    }

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 20,
            initialLoadSize = 60
        )
    }
}
