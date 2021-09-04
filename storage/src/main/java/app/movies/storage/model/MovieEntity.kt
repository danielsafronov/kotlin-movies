package app.movies.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val sid: Long,
    val title: String,
    val description: String,
    val rating: Double,
    val posterPath: String,
)
