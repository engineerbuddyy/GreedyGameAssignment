package com.example.greedygamequoteappassignment.Presentation.Nav

import HomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greedygamequoteappassignment.Presentation.DetailScreen
import com.example.greedygamequoteappassignment.Presentation.FavouriteScreen
import com.example.greedygamequoteappassignment.viewModel.QuoteViewModel
import androidx.compose.runtime.collectAsState

sealed class QuoteScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : QuoteScreen("home_screen", "Home", Icons.Default.Home)
    object Favourite : QuoteScreen("favourite_screen", "Favourite", Icons.Default.Favorite)
    object Detail : QuoteScreen("detail_screen/{quoteId}", "Detail", Icons.Default.Favorite) {    // /// CHANGED
        fun createRoute(quoteId: String) = "detail_screen/$quoteId"
    }

}


@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: QuoteViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = QuoteScreen.Home.route,
        modifier = modifier
    ) {
        // Home
        composable(QuoteScreen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }


        composable(route = QuoteScreen.Detail.route) { backStackEntry ->   // /// CHANGED
            val quoteId = backStackEntry.arguments?.getString("quoteId")    // /// CHANGED
            val quote = viewModel.quotes.collectAsState().value.find { it.id == quoteId }   // /// CHANGED
            if (quote != null) {
                DetailScreen(
                    navController = navController, quote = quote,
                    viewModel = viewModel
                )
            }

        }


        composable(QuoteScreen.Favourite.route) {
            FavouriteScreen(viewModel = viewModel, navController = navController)
        }
    }
}
