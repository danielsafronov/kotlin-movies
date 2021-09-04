package app.movies

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Named("tmdb-api-key")
    internal fun provideTmdbApiKey(): String = BuildConfig.TMDB_API_KEY
}
