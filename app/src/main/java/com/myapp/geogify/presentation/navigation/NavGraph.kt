package com.myapp.geogify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapp.geogify.presentation.screens.detail.CountryDetailScreen
import com.myapp.geogify.presentation.screens.home.HomeScreen

sealed class Screen(
    val route: String,
) {
    object Home : Screen("home")

    object Detail : Screen("detail/{countryId}") {
        fun createRoute(countryId: String) = "detail/$countryId"
    }
}

@Composable
fun GeogifyNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onCountryClick = { countryCode ->
                    navController.navigate(Screen.Detail.createRoute(countryCode))
                },
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("countryId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val countryId = backStackEntry.arguments?.getString("countryId") ?: return@composable
            CountryDetailScreen(
                code = countryId,
                onRetry = {
                },
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
