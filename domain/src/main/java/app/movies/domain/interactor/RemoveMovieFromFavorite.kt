package app.movies.domain.interactor

import app.movies.data.repository.storage.FavoriteMovieStorageRepository
import javax.inject.Inject

class RemoveMovieFromFavorite @Inject constructor(
    private val favoriteMovieStorageRepository: FavoriteMovieStorageRepository,
) {
    suspend operator fun invoke(params: Params) {
        favoriteMovieStorageRepository.removeMovieFromFavorites(params.favoriteMovieId)
    }

    data class Params(val favoriteMovieId: Long)
}
