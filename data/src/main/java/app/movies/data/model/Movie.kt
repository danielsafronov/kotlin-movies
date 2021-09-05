package app.movies.data.model

data class Movie(
    val id: Long,
    val sid: Long,
    val title: String,
    val description: String,
    val rating: Double,
    val posterUrl: String?,
    val page: Int,
)
