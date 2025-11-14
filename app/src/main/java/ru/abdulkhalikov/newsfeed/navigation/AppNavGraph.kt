package ru.abdulkhalikov.newsfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreen: @Composable () -> Unit,
    favoriteScreen: @Composable () -> Unit,
    profileScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsFeed.route
    ) {
        composable(Screen.NewsFeed.route) {
            newsFeedScreen()
        }
        composable(Screen.Favourite.route) {
            favoriteScreen()
        }
        composable(Screen.Profile.route) {
            profileScreen()
        }
    }
}