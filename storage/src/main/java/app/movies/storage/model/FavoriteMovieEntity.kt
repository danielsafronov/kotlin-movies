package app.movies.storage.model

import androidx.room.*

@Entity(
    tableName = "favorite_movies",
    indices = [
        Index(value = ["movie_id"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class FavoriteMovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "movie_id") val movieId: Long,
)
