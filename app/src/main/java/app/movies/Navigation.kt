package app.movies

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.movies.home.Home
import app.movies.movielist.MovieList
import app.movies.movielist.MovieListMode

internal sealed class Destinations(val path: String) {
    object MoviesHome : Destinations("movies")
}

@Composable
internal fun MoviesNavigation(
    controller: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = controller,
        startDestination = Destinations.MoviesHome.path,
    ) {
        composable(Destinations.MoviesHome.path) {
            Home(
                films = { MovieList(mode = MovieListMode.All) },
                favorites = { MovieList(mode = MovieListMode.Favorites) }
            )
        }
    }
}
