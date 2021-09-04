package app.movies.network.model

import com.google.gson.annotations.SerializedName

data class PageDto(
    @SerializedName("page") val page: Long,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Long,
    @SerializedName("total_results") val totalResults: Long,
)