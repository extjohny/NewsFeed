package ru.abdulkhalikov.newsfeed.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.abdulkhalikov.newsfeed.R
import ru.abdulkhalikov.newsfeed.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {

    data object Home : NavigationItem(
        screen = Screen.NewsFeed,
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )

    data object Favorite : NavigationItem(
        screen = Screen.Favourite,
        titleResId = R.string.navigation_item_favorite,
        icon = Icons.Outlined.Favorite
    )

    data object Profile : NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}