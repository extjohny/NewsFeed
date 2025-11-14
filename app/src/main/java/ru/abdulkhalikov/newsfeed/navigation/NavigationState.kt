package ru.abdulkhalikov.newsfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.startDestinationId) { saveState = true }
            restoreState = true
            launchSingleTop = true
        }
    }
}

@Composable
fun rememberNavigationState(): NavigationState {
    val navHostController: NavHostController = rememberNavController()
    return NavigationState(navHostController)
}