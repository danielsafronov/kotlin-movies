package app.movies.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("poster_path") val posterPath: String? = null,
)