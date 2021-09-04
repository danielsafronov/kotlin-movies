package app.movies.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.movies.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
internal class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {
    private val mode: MutableStateFlow<MovieListState.Mode> = MutableStateFlow(MovieListState.Mode.All)
    private val loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

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

    fun setMode(mode: MovieListState.Mode) {
        this.mode.value = mode
    }
}