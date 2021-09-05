package app.movies.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies",
    indices = [
        Index(value = ["sid"], unique = true)
    ]
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "sid") val sid: Long = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "rating") val rating: Double = 0.0,
    @ColumnInfo(name = "poster_url") val posterUrl: String? = null,
    @ColumnInfo(name = "page") val page: Int = 0,
)
