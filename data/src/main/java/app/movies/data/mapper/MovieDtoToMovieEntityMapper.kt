package app.movies.data.mapper

import app.movies.base.Mapper
import app.movies.network.model.MovieDto
import app.movies.storage.model.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDtoToMovieEntityMapper @Inject constructor(): Mapper<MovieDto, MovieEntity> {
    override suspend fun map(from: MovieDto): MovieEntity = MovieEntity(
        sid = from.id,
        title = from.title,
        description = from.overview,
        rating = from.voteAverage,
        posterUrl = "https://image.tmdb.org/t/p/w500${from.posterPath}",
    )
}
