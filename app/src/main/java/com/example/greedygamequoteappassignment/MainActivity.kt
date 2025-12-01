package com.example.greedygamequoteappassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.greedygamequoteappassignment.Presentation.Nav.AppNavGraph
import com.example.greedygamequoteappassignment.Presentation.Nav.FloatingNavigationBar
import com.example.greedygamequoteappassignment.data.FavoriteManager
import com.example.greedygamequoteappassignment.ui.theme.GreedyGameQuoteAppAssignmentTheme
import com.example.greedygamequoteappassignment.viewModel.QuoteViewModel
import com.example.greedygamequoteappassignment.viewModel.QuoteViewModelFactory
import androidx.compose.foundation.layout.WindowInsets

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val favoriteManager = FavoriteManager(this)
        val viewModel: QuoteViewModel by viewModels {
            QuoteViewModelFactory(favoriteManager)
        }

        // Edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            GreedyGameQuoteAppAssignmentTheme {
                val navController = rememberNavController()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                        )
                ) {

                    AppNavGraph(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.fillMaxSize()
                    )


                    FloatingNavigationBar(navController = navController)
                }
            }
        }
    }
}
