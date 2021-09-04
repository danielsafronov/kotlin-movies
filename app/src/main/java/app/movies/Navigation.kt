package app.movies

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.movies.home.Home

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
                films = { Text(text = "Films") },
                favorites = { Text(text = "Favorites") }
            )
        }
    }
}
