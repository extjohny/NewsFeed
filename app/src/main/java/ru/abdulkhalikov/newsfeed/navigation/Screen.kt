package ru.abdulkhalikov.newsfeed.navigation

sealed class Screen(val route: String) {

    data object NewsFeed : Screen(ROUTE_NEWS_FEED)

    data object Favourite : Screen(ROUTE_FAVOURITE)

    data object Profile : Screen(ROUTE_PROFILE)

    companion object {

        const val ROUTE_NEWS_FEED = "home"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}