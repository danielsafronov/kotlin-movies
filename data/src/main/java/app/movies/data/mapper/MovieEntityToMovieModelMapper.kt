package app.movies.data.mapper

import app.movies.base.Mapper
import app.movies.data.model.Movie
import app.movies.storage.model.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieEntityToMovieModelMapper @Inject constructor(): Mapper<MovieEntity, Movie> {
    override suspend fun map(from: MovieEntity): Movie = Movie(
        id = from.id,
        sid = from.sid,
        title = from.title,
        description = from.description,
        rating = from.rating,
        posterUrl = from.posterUrl,
        page = from.page,
    )
}
