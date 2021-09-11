package app.movies.domain.interactor

import app.movies.data.store.MovieStore
import javax.inject.Inject

class UpdateMovies @Inject constructor(private val store: MovieStore) {
    suspend operator fun invoke(params: Params) {
        store.fetch(page = params.page)
    }

    data class Params(
        val page: Int,
        val force: Boolean,
    )
}