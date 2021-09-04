package app.movies.movielist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
internal class MovieListViewModel @Inject constructor() : ViewModel() {
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