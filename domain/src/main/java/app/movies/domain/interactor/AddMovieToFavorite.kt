package app.movies.domain.interactor

import app.movies.data.repository.storage.FavoriteMovieStorageRepository
import javax.inject.Inject

class AddMovieToFavorite @Inject constructor(private val favoriteMovieStorageRepository: FavoriteMovieStorageRepository) {
    suspend operator fun invoke(params: Params) {
        favoriteMovieStorageRepository.addMovieToFavorites(params.movieId)
    }

    data class Params(val movieId: Long)
}
