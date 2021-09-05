package app.movies.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import app.movies.domain.observer.ObservePagedMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieListViewModel @Inject constructor(
    private val observePagedMovies: ObservePagedMovies,
) : ViewModel() {
    private val mode: MutableStateFlow<MovieListState.Mode> = MutableStateFlow(MovieListState.Mode.All)
    private val loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val pagedMovies = observePagedMovies.flow.cachedIn(viewModelScope)

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

    init {
//        viewModelScope.launch {
//            state.collect { state ->
//                observePagedMovies(ObservePagedMovies.Params(PAGING_CONFIG))
//            }
//        }
        observePagedMovies(ObservePagedMovies.Params(PAGING_CONFIG))
    }

    fun setMode(mode: MovieListState.Mode) {
        this.mode.value = mode
    }

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 20,
            initialLoadSize = 60
        )
    }
}